from flask import Flask
from service.messageService import MessageService
from service.expense import Expense
from kafka import KafkaProducer
import json
from flask import request,jsonify
app =Flask(__name__)
app.config.from_pyfile('config.py')

messages =MessageService()
producer =KafkaProducer(bootstrap_servers=['127.0.0.1:9092'],value_serializer=lambda v:json.dumps(v).encode('utf-8'))

@app.route('/v1/ds/message',methods =['POST'])
def handle_message():
    message=request.json.get('message')
    result=messages.process_message(message=message)
    s_result=result.json()
    producer.send("expense",s_result)

    return jsonify(result)




if __name__ =="__main__":
    app.run(host="localhost",port=8000,debug=True)