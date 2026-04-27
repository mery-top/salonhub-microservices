from fastapi import FastAPI, UploadFile, File
import cv2
import numpy as np
import mediapipe as mp
import io
from PIL import Image

app = FastAPI()

# ---------------- MEDIA PIPE SETUP ----------------
mp_face_mesh = mp.solutions.face_mesh
face_mesh = mp_face_mesh.FaceMesh(static_image_mode=True)

def read_image(file_bytes):
    image = Image.open(io.BytesIO(file_bytes))
    image = np.array(image)
    return cv2.cvtColor(image, cv2.COLOR_RGB2BGR)

def get_landmarks(image):
    rgb = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
    results = face_mesh.process(rgb)

    if not results.multi_face_landmarks:
        return None

    landmarks = results.multi_face_landmarks[0].landmark
    h, w, _ = image.shape

    points = [(int(l.x * w), int(l.y * h)) for l in landmarks]
    return points

def euclidean(p1, p2):
    return np.linalg.norm(np.array(p1) - np.array(p2))

def classify_face(points):
    chin = points[152]
    forehead = points[10]
    left_cheek = points[234]
    right_cheek = points[454]
    left_jaw = points[172]
    right_jaw = points[397]

    face_height = euclidean(chin, forehead)
    face_width = euclidean(left_cheek, right_cheek)
    jaw_width = euclidean(left_jaw, right_jaw)

    face_ratio = face_height / face_width
    jaw_ratio = jaw_width / face_width

    if face_ratio > 1.3:
        return "long"

    if jaw_ratio > 0.9:
        return "square"

    if face_ratio < 1.1 and jaw_ratio < 0.8:
        return "round"

    if jaw_width < face_width * 0.75:
        return "heart"

    return "oval"

def detect_skin_tone(image):
    hsv = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)

    h, s, v = cv2.split(hsv)

    brightness = np.mean(v)
    saturation = np.mean(s)

    if brightness < 80:
        return "deep"
    elif brightness < 130:
        return "medium"
    elif brightness < 180:
        return "fair"
    else:
        return "very fair"

def detect_hair_type(image):
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    edges = cv2.Canny(gray, 100, 200)

    edge_density = np.mean(edges)

    if edge_density > 20:
        return "curly"
    elif edge_density > 10:
        return "wavy"
    else:
        return "straight"

@app.post("/analyze")
async def analyze(file: UploadFile = File(...)):
    image_bytes = await file.read()
    image = read_image(image_bytes)

    points = get_landmarks(image)

    if points is None:
        return {"error": "No face detected"}

    return {
        "faceShape": classify_face(points),
        "skinTone": detect_skin_tone(image),
        "hairType": detect_hair_type(image)
    }