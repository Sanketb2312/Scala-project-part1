import exceptions._

class Account(val bank: Bank, initialBalance: Double) {

    class Balance(var amount: Double) {}

    val balance = new Balance(initialBalance)

    // TODO
    // for project task 1.2: implement functions
    // for project task 1.3: change return type and update function bodies
    def withdraw(amount: Double): Unit = {
        if (amount-balance < 0) {
            throw new IllegalArgumentException("Not enough money to withdraw")
        }
        this.balance-=amount
    }
    def deposit (amount: Double): Unit = {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit cannot be negative")
        }
        this.balance+=amount

    }
    def getBalanceAmount: Double = {
        return this.balance
    }

    def transferTo(account: Account, amount: Double) = {
        bank addTransactionToQueue (this, account, amount)
    }


}
