from fastapi import FastAPI, UploadFile, File
from PIL import Image
import io
import torch
from transformers import CLIPProcessor, CLIPModel
from fastapi.responses import Response

app = FastAPI()

model = CLIPModel.from_pretrained("openai/clip-vit-base-patch32")
processor = CLIPProcessor.from_pretrained("openai/clip-vit-base-patch32")

@app.post("/embed")
async def embed_image(file: UploadFile = File(...)):
    image_bytes = await file.read()
    image = Image.open(io.BytesIO(image_bytes)).convert("RGB")

    inputs = processor(images=image, return_tensors="pt")

    with torch.no_grad():
        features = model.get_image_features(**inputs)

    vec = features[0].cpu().numpy().astype("float32")

    return Response(
        content=vec.tobytes(),
        media_type="application/octet-stream"
    )