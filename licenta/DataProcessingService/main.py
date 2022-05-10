import uvicorn
if __name__ == '__main__':
    uvicorn.run("controller:app", host="0.0.0.0", port=5920)