/**
 * Created by parischrysanthou on 19/04/2018.
 */
public class TransactionInput {
    public String transactionOutputId;
    public TransactionOutput UTXO;

    public TransactionInput(String transactionOutputId){
        this.transactionOutputId  = transactionOutputId;
    }
}
