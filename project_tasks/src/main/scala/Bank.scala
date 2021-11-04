class Bank(val allowedAttempts: Integer = 3) {

  private val transactionsQueue: TransactionQueue = new TransactionQueue()
  private val processedTransactions: TransactionQueue = new TransactionQueue()
  private var isProcessingTransactions: Boolean = false

  def addTransactionToQueue(
      from: Account,
      to: Account,
      amount: Double
  ): Unit = {
    val transaction = new Transaction(
      this.transactionsQueue,
      this.processedTransactions,
      from,
      to,
      amount,
      this.allowedAttempts
    )

    this.transactionsQueue.push(transaction)

    if (!isProcessingTransactions) {
      this.isProcessingTransactions = true
      val thread = new Thread(() => processTransactions)
      thread.start  
    }
  }
  // project task 2
  // create a new transaction object and put it in the queue
  // spawn a thread that calls processTransactions

  private def processTransactions: Unit = {
    if (!transactionsQueue.isEmpty) {
      val trans = transactionsQueue.pop
      val thread = new Thread(trans)
      thread.start()
    }

    processTransactions
  }

  // Function that pops a transaction from the queue
  // and spawns a thread to execute the transaction.
  // Finally do the appropriate thing, depending on whether
  // the transaction succeeded or not

  def addAccount(initialBalance: Double): Account = {
    new Account(this, initialBalance)
  }

  def getProcessedTransactionsAsList: List[Transaction] = {
    processedTransactions.iterator.toList
  }
}
