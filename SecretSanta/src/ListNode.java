public class ListNode {
    ListNode nextNode;
    ListNode prevNode;
    String data;

    public ListNode(String data){
        this.data = data;
        nextNode = null;
        prevNode = null;
    }

    public ListNode(String data, ListNode prevNode, ListNode nextNode){
        this(data);
        this.nextNode = nextNode;
        this.prevNode = prevNode;
    }

    public ListNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(ListNode nextNode) {
        this.nextNode = nextNode;
    }

    public ListNode getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(ListNode prevNode) {
        this.prevNode = prevNode;
    }

    public void setPrevAndNext(ListNode prevNode, ListNode nextNode){
        this.prevNode = prevNode;
        this.nextNode = nextNode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
