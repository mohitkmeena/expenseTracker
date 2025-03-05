from langchain_core.pydantic_v1 import BaseModel ,Field
from typing import Optional
class Expense (BaseModel):
    amount:Optional[str]=Field(title="expense",description="Expense made in transaction")
    merchant:Optional[str]=Field(title="expense",description="Merchant anme to whom transaction is made")
    currency:Optional[str]=Field(title="expense",description="currency of transaction")

    def to_dict(self):
        return {
            "amount": self.amount,
            "merchant": self.merchant,
            "currency": self.currency
        }

