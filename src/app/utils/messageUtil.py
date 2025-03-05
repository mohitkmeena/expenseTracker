import re
class MessageUtil:
    
    def isBankSms(self,messages):
        words_to_search=['spent','card','bank']
        pattern= r'\b(?:' +'|'.join(re.escape (word) for word in words_to_search)+r')\b'
        return bool(re.search(pattern,messages,re.IGNORECASE))