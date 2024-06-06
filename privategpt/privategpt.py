from gpt4all import GPT4All
model = GPT4All("/home/martinviewanalytics/mistral-7b-openorca.gguf2.Q4_0.gguf", device="cpu")
output = model.generate("The capital of France is ", max_tokens=3)
print(output)
