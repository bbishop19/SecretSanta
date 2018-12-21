import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.Iterator;

public class CircularLinkedList implements Iterable<String>{
    private ListNode header;
    private int size;

    public CircularLinkedList(){
        header = null;
        size = 0;
    }

    public void generateSantas(Hashtable<String, String> emailTable){
        //Fisher-Yates shuffle
        //runs O(n^2) because iterating linked lists is inefficient
        for(int i = 0; i < size -2; i++){
            int j = (int)(Math.random()*(size-i))+i;
            swap(i,j);
        }

        PrintWriter writer = null;
        try{
            writer = new PrintWriter("SecretSantas.txt");
        }catch(FileNotFoundException e){
            //don't do anything gottem
        }

        ListNode currNode = header;
        for(int i = 0; i < size; i++){
            writer.println(currNode.getData() + " is giving to " + currNode.getNextNode().getData());

            //not my code
            // get the desktop instance of the current environment
            Desktop desktop = Desktop.getDesktop();

            try {
                // encode the strings
                String to = URLEncoder.encode(emailTable.get(currNode.getData()), "UTF-8");
                String subject = URLEncoder.encode("Your Secret Santa", "UTF-8").replace("+", "%20");//this title is incorrect because the secret santa is the one giving
                String body = URLEncoder.encode("You're giving gifts to " + currNode.getNextNode().getData(), "UTF-8").replace("+", "%20");

                // create a URI string and send the mailto request to the desktop default client
                String uriString = String.format("mailto:%s?subject=%s&body=%s", to, subject, body);
                desktop.mail(new URI(uriString));
            }catch(URISyntaxException | IOException e){};

            currNode = currNode.getNextNode();
        }

        writer.close();
    }


    public void add(String data){
        ListNode newNode = new ListNode(data);
        if(header == null){
            header = newNode;
            header.setPrevAndNext(header, header);
        }else{
            newNode.setPrevAndNext(header.getPrevNode(), header);
            header.getPrevNode().setNextNode(newNode);
            header.setPrevNode(newNode);
        }
        size++;
    }

    /*public String remove(){
        if(header == null){
            return null;
        }else {
            String toBeRemoved = header.getPrevNode().getData();
            header.setPrevNode(header.getPrevNode().getPrevNode());
            header.getPrevNode().setNextNode(header);
            size--;
            return toBeRemoved;
        }

    }*/

    public String get(int index){
        ListNode currentNode = header;
        for(int i = 0; i < index; i++){
            currentNode = currentNode.getNextNode();
        }
        return currentNode.getData();
    }

    private void swap(int index1, int index2){
        ListNode node1 = header;
        ListNode node2 = header;
        for(int i = 0; i < Math.max(index1, index2); i++){
            if(i<index1){
                node1 = node1.getNextNode();
            }
            if(i<index2){
                node2 = node2.getNextNode();
            }
        }
        String temp = node1.getData();
        node1.setData(node2.getData());
        node2.setData(temp);
    }


    @Override
    public Iterator<String> iterator() {
        return new CLLIterator();
    }

    private class CLLIterator implements Iterator<String>{
        ListNode currentNode = header;
        boolean lazySolution = true;

        @Override
        public boolean hasNext() {
            if(lazySolution){
                return true;
            }else {
                return !currentNode.getNextNode().equals(header);
            }
        }

        @Override
        public String next() {
            if(lazySolution){
                lazySolution = false;
                return currentNode.getData();
            }else {
                currentNode = currentNode.getNextNode();
                return currentNode.getData();
            }
        }
    }
}


