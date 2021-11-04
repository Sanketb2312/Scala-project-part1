import scala.collection.mutable
import scala.collection.mutable.Queue

object TransactionStatus extends Enumeration {
  val SUCCESS, PENDING, FAILED = Value
}

class TransactionQueue {

  // TODO
  // project task 1.1
  // Add datastructure to contain the transactions
  val transactions = new Queue[Transaction]()

  // Remove and return the first element from the queue
  def pop: Transaction = this.synchronized { transactions.dequeue() }

  // Return whether the queue is empty
  def isEmpty: Boolean = this.synchronized { transactions.isEmpty }

  // Add new element to the back of the queue
  def push(t: Transaction): Unit = this.synchronized { 
    transactions += t
    // this.notifyAll()  
  }

  // Return the first element from the queue without removing it
  def peek: Transaction = this.synchronized { transactions.head }

  // Return an iterator to allow you to iterate over the queue
  def iterator: Iterator[Transaction] = transactions.iterator
}
class Transaction(
    val transactionsQueue: TransactionQueue,
    val processedTransactions: TransactionQueue,
    val from: Account,
    val to: Account,
    val amount: Double,
    val allowedAttemps: Int
) extends Runnable {

  var status: TransactionStatus.Value = TransactionStatus.PENDING
  var attempt = 0

  override def run: Unit = {
    def doTransaction: Either[Unit, String] = {
      // TODO - project task 3
      // Extend this method to satisfy requirements.
      from.withdraw(this.amount) match {
        case Left(_) => {
          to.deposit(this.amount) match {
            case Left(_) => Left(())
            case Right(error) => {
              from.deposit(this.amount)
              Right("Deposit to account failed with error: " + error)
            }
          }
        }
        case Right(error) => {  
          Right("Withdrawal from account failed with error: " + error)
        }
      }
    }

    this.synchronized {
      if (status == TransactionStatus.PENDING) {
        val transactionResult: Either[Unit, String] = doTransaction
        Thread.sleep(50)

        transactionResult match {
          case Left(_) => {
            status = TransactionStatus.SUCCESS
            this.processedTransactions.push(this)
          } case Right(error) => {
            attempt += 1
            if (attempt >= allowedAttemps) {
              status = TransactionStatus.FAILED
              this.processedTransactions.push(this)
            } else {
              this.transactionsQueue.push(this)
            }
          }
        }
      }
    }
  }
}


