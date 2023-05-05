import java.util.Iterator;
import java.util.ArrayList;

public class FileStructure {

	private NLNode<FileObject> root;
	
	public FileStructure (String fileObjectName) throws FileObjectException {
				
		try {
			// create our file object and the root
			FileObject fileObject = new FileObject(fileObjectName);
			root = new NLNode<>(fileObject, null);
		    
			// if the file is a directory to more files, build a tree from the contents
		    if (fileObject.isDirectory()) {
		    	AUXFileStructure(root);
		    } // if
		} // try
		
		catch (FileObjectException e) {
			throw e; // if any error found, catch it
		} // catch
		
	} // method FileStructure - constructor
	
	
	// auxilary recusive algorithm
	private void AUXFileStructure(NLNode<FileObject> node) {
	    // base case
		if (node.getData().isDirectory()) {
	        Iterator<FileObject> iterator = node.getData().directoryFiles();
	        
	        // recursive case
	        while (iterator.hasNext()) {

	        	// Create a new node object representing the next FileObject in the iterator
	            FileObject fileObject = iterator.next();
	            NLNode<FileObject> childNode = new NLNode<>(fileObject, node);

	            // Set the new NLNode as a child of the current node
	            node.addChild(childNode);
	            // recursive line
	            AUXFileStructure(childNode);
	        } // while
	    } // if
	
	} // method AUXFileStructure

	
	public NLNode<FileObject> getRoot() {
	    return root;
	} // method getRoot
	
	
	public Iterator<String> filesOfType(String type) {
	    ArrayList<String> container = new ArrayList<String>();
	    AUXfilesOfType(root, type, container);
	    
	    return container.iterator();
	} // method 

	
	// auxilary recusive algorithm
	private void AUXfilesOfType(NLNode<FileObject> node, String type, ArrayList<String> container) {
	    
		// base case
	    if (!node.getData().isDirectory()) {  // node is a file, not directory
	        String fileName = node.getData().getLongName();
	        
	        // if very end of directory line, add file name to the container
	        if (fileName.endsWith(type)) {
	        	container.add(fileName);
	        } // if
	        
	    } // if
	    
	    // recursive case
	    else {  // node is a directory
	        Iterator<NLNode<FileObject>> children = node.getChildren();
	        
	        // get to the very end of directory line
	        while (children.hasNext()) {
	            NLNode<FileObject> child = children.next();
	            // recursive line
	            AUXfilesOfType(child, type, container);
	        } // while
	    } // else
	    
	} // method AUXfilesOfType - getter
	
	
	public String findFile(String name) { 
	    String fullName = AUXfindFile(root, name);
	    return fullName;
	} // method findFile
	
	
	// this method was based on AUXFilesOfType
	public String AUXfindFile(NLNode<FileObject> node, String name) {
		// base case
		// node is a file, not directory
	    if (!node.getData().isDirectory()) {
	        String fileName = node.getData().getName();
	        
	        // if its the desired file, return the full name
	        if (fileName.equals(name)) {
	            String fullName = node.getData().getLongName();
	            return fullName;
	        } // if
	        
	    } // if
		
	    else {
	    	Iterator<NLNode<FileObject>> children = node.getChildren();
	        
	        // get to the very end of directory line
	        while (children.hasNext()) {
	            NLNode<FileObject> child = children.next();
	            
	            // recursive line
	            String fullName = AUXfindFile(child, name);
	            
	            // if fullName didn't return "", return the full name of the file
	            if (fullName !=  "") {
	                return fullName;
	            } // if
	            
	        } // while
	    } // else
		    
	    return "";
	} // method AUXfindFile - getter
	
} // class FileStructure
