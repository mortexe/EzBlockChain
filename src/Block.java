import java.util.ArrayList;
import java.util.Date;

/**
 * Created by parischrysanthou on 19/04/2018.
 */
public class Block {

    public String hash;
    public String previousHash;
    public String merkleRoot;
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private long timeStamp;
    private int nonce;

    //Block Constructor
    public  Block(String previousHash){
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash(){
        String calculatedhash = StringUntil.applySha256(
                previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + merkleRoot
        );
        return calculatedhash;
    }

    public void mineBlock( int difficulty) {
        merkleRoot = StringUntil.getMerkleRoot(transactions);
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)){
            nonce++;
            hash = calculateHash();
        }

        System.out.println("Block Mined : " + hash);
    }

    public boolean addTransaction(Transaction transaction){
        if(transaction == null) return false;
        if((previousHash != "0")){
            if ((!transaction.processTransaction())) {
                System.out.println("Transaction failed to process. Discarded.");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }
}
