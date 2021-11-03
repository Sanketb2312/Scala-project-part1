class Account(val bank: Bank, initialBalance: Double) {

  class Balance(var amount: Double) {}

  val balance = new Balance(initialBalance)

  // TODO
  // for project task 1.3: change return type and update function bodies
  def withdraw(amount: Double): Either[Unit, String] = this.synchronized {
    if (amount < 0) {
      Right("Withdrawal cannot be negative")
    } else if (balance.amount - amount < 0) {
      Right("Not enough money to withdraw")
    } else {
      this.balance.amount -= amount
      Left(())
    }

  }

  def deposit(amount: Double): Either[Unit, String] = this.synchronized {
    if (amount < 0) {
      Right("Deposit cannot be negative")
    } else {
      this.balance.amount += amount
      Left(())
    }
  }

  def getBalanceAmount: Double = {
    return this.balance.amount
  }

  def transferTo(account: Account, amount: Double) = {
    bank.addTransactionToQueue(this, account, amount)
  }

}
