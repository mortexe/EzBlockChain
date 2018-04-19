import java.security.PublicKey;
/**
 * Created by parischrysanthou on 19/04/2018.
 */
public class TransactionOutput {
    public String id;
    public PublicKey reciepient; //also known as the new owner of these coins.
    public float value; //the amount of coins they own
    public String parentTransactionId; //the id of the transaction this output was created in

    public TransactionOutput(PublicKey reciepient, float value, String parentTransactionId){
        this.reciepient = reciepient;
        this.value = value;
        this.parentTransactionId = parentTransactionId;
        this.id = StringUntil.applySha256(StringUntil.getStringFromKey(reciepient) + Float.toString(value) + parentTransactionId);
    }

    //Check if coin belongs to you
    public boolean isMine(PublicKey publicKey){
        return (publicKey == reciepient);
    }
}
