from fastapi import FastAPI, Body
import uvicorn
from starlette.middleware.cors import CORSMiddleware
from pydantic import BaseModel
import pandas as pd
import service
import RandomForest

app = FastAPI()

origins = [
    "http://localhost:3000",
    "http://127.0.0.1:3000",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    # allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


class Item(BaseModel):
    symptoms: str


@app.post("/test")
async def met(payload: Item):
    parsedList = service.configureTestDataset(payload.symptoms)
    return service.predictDisease()


