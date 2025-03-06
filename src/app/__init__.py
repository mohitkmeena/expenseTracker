from flask import Flask
from service.messageService import MessageService
from service.expense import Expense
from kafka import KafkaProducer
import json
import os
from flask import request,jsonify
app =Flask(__name__)
app.config.from_pyfile('config.py')

messages =MessageService()
kafka_host=os.getenv('KAFKA_HOST','localhost')
kafka_port=os.getenv('KAFKA_PORT','9092')
kafka_server=f'{kafka_host}:{kafka_port}'
producer =KafkaProducer(bootstrap_servers=[kafka_server],value_serializer=lambda v:json.dumps(v).encode('utf-8'))

@app.route('/api/v1/data-extract',methods =['POST'])
def handle_message():
    message=request.json.get('message')
    result=messages.process_message(message=message)
    s_result=result.json()
    producer.send("expense",s_result)
    return jsonify(result)

if __name__ =="__main__":
    app.run(host="localhost",port=8000,debug=True)