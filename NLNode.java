import java.util.Comparator;
import java.util.Iterator;

public class NLNode <T> {
	
	private NLNode<T> parent;
	private ListNodes<NLNode<T>> children;
	private T data;
	
	// bare constructor
    public NLNode() {
        this.data = null;
        this.parent = null;
        this.children = new ListNodes<NLNode<T>>();
    } // constructor NLNode
    
    // Constructor with parameters
    public NLNode(T d, NLNode<T> p) {
        this.data = d;
        this.parent = p;
        this.children = new ListNodes<NLNode<T>>();
    } // constructor NLNode
    
    
    
    public void setParent(NLNode<T> p) {
    	this.parent = p;
    } // method setParent
    
    
    public NLNode<T> getParent() {
        return parent;
    } // method getParent
    
    
    
    
    public void addChild(NLNode<T> newChild) {
    	newChild.setParent(this);
        this.children.add(newChild);
    } // method addChild
    
    
    // bare getChildren
    public Iterator<NLNode<T>> getChildren() {
        return children.getList();
    } // method getChildren -- Iterator
    
    
    // getChildren with parameters    
    public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter) {
        ListNodes<NLNode<T>> sortedChildrenList = new ListNodes<NLNode<T>>();
        Iterator<NLNode<T>> childrenIterator = children.sortedList(sorter);
        
        // loop over the sorted children and add to ListNodes object
        while (childrenIterator.hasNext()) {
            sortedChildrenList.add(childrenIterator.next());
        } // while
        
        return sortedChildrenList.getList();
    } // method getChildren -- Iterator
    
    
    public T getData() {
        return this.data;
    } // method getData

    
    public void setData(T d) {
        this.data = d;
    } // method setData

    
} // class NLNode
