from flask import Flask
from service.messageService import MessageService
from service.expense import Expense
from flask import request,jsonify
app =Flask(__name__)
app.config.from_pyfile('config.py')

messages =MessageService()

@app.route('/v1/ds/message',methods =['POST'])
def handle_message():
    message=request.json.get('message')
    result=messages.process_message(message=message)
    print(result)
    if isinstance (result,Expense):
       return jsonify(result.to_dict())
    return jsonify({"response": result}) 

@app.route('/v1/ds/get',methods =['GET'])
def handle_get():
    print('hello world')



if __name__ =="__main__":
    app.run(host="localhost",port=8004,debug=True)