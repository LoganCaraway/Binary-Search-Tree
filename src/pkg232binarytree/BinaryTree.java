/*
 * Author: Logan Caraway
 * Date Created: 5/16/2018
 * Purpose: It's a binary tree.
 */
package pkg232binarytree;

public class BinaryTree<E extends Comparable> {
    //--------------------Nested-Node-Class--------------------//
    private class Node<E> {
        private final E key;
        private Node left_child, right_child;
        
        /*Node constructor*/
        public Node(E key) {
            this.key = key;
        }
        
        /*Method to get the (unchangable) value stored in this node*/
        public E getKey() {return key;}
        
        /*Methods for retrieving child nodes*/
        public Node getLeft() {return left_child;}
        public Node getRight() {return right_child;}
        
        /*Methods for setting child nodes*/
        public void setLeft(Node left) {left_child = left;}
        public void setRight(Node right) {right_child = right;}
    }
    //---------------------------------------------------------//
    
    //BinaryTree field variables
    private Node root;
    
    /*BinaryTree constructor*/
    public BinaryTree() {}
    
    //------------------------------------------------------------------------------------------//
    /*Adds a node with the given key to the tree if it is not already present.
    NOTES: uses a private helper method.*/
    public void add(E key) {
        root = addNodeToTree(root, key);
    }
    /*private helper method for "add" method*/
    private Node addNodeToTree(Node currentNode, E key) {
        //recursive base case
        if (currentNode == null) {
            return new Node(key);
        }
        int cmp = key.compareTo(currentNode.getKey());
        
        //key to add is less than current node, go left
        if (cmp < 0) currentNode.setLeft(addNodeToTree(currentNode.getLeft(), key));
        //key to add is greater than current node, go right
        else if (cmp > 0) currentNode.setRight(addNodeToTree(currentNode.getRight(), key));
        
        return currentNode;
    }
    
    //------------------------------------------------------------------------------------------//
    /*Deletes the node with the given key from the tree if it is present.
    NOTES: uses a private helper method.*/
    public void delete(E key) {
        root = deleteNodeFromTree(root, key);
    }
    /*private helper method for "add" method*/
    private Node deleteNodeFromTree(Node currentNode, E key) {
        //if we reach the end of the tree without finding the given key, skip strait to return statement
        if (currentNode != null) {
            int cmp = key.compareTo(currentNode.getKey());
            //key to delete is less than current node, go left
            if (cmp < 0) currentNode.setLeft(deleteNodeFromTree(currentNode.getLeft(), key));
            //key to delete is greater than current node, go right
            else if (cmp > 0) currentNode.setRight(deleteNodeFromTree(currentNode.getRight(), key));
            //we found key to delete
            else {
                //only one child, replace currentNode with that child
                if (currentNode.getRight() == null) return currentNode.getLeft();
                if (currentNode.getLeft() == null) return currentNode.getRight();
                
                //node has two children, replace with next inorder node
                Node node_to_be_deleted = currentNode;
                currentNode = minNode(node_to_be_deleted.getRight());
                currentNode.setRight(deleteMinNode(node_to_be_deleted.getRight()));
                currentNode.setLeft(node_to_be_deleted.getLeft());
            }
        }
        return currentNode;
    }
    /*Deletes Node containing smallest key greater than that of the given Node*/
    private Node deleteMinNode(Node currentNode) {
        if (currentNode.getLeft() == null) return currentNode.getRight();
        currentNode.setLeft(deleteMinNode(currentNode.getLeft()));
        return currentNode;
    }
    
    //------------------------------------------------------------------------------------------//
    /*Searches tree for a given key, returns true if found
    NOTES: uses a private helper method.*/
    public boolean search(E key) {
        if (root == null) return false;
        return searchTreeForKey(root, key);
    }
    /*Searches tree for a given key*/
    private boolean searchTreeForKey(Node currentNode, E key) {
        //given key is not in the tree
        if (currentNode == null) return false;
        
        int cmp = key.compareTo(currentNode.getKey());
        
        //if key to be found is less than that of the currentNode's, go left. If greater, go right
        if (cmp < 0) return searchTreeForKey(currentNode.getLeft(), key);
        if (cmp > 0) return searchTreeForKey(currentNode.getRight(), key);
        
        //current node contains value to be found
        return true;
    }
    //------------------------------------------------------------------------------------------//
    /*Prints the tree Inorder (left child, parent, right child)
    NOTES: uses a private helper method.*/
    public void printInorderToConsole() {
        System.out.println("Inorder: ");
        printInorderToConsole(root);
        System.out.println();
    }
    /*private helper method for "printInorderToConsole" method*/
    private void printInorderToConsole(Node currentNode) {
        if (currentNode != null) {
            printInorderToConsole(currentNode.getLeft());
            System.out.print(currentNode.getKey().toString()+" | ");
            printInorderToConsole(currentNode.getRight());
        }
    }
    
    //------------------------------------------------------------------------------------------//
    /*Prints the tree in Preorder (parent, left child, right child)
    NOTES: uses a private helper method.*/
    public void printPreorderToConsole() {
        System.out.println("Preorder: ");
        printPreorderToConsole(root);
        System.out.println();
    }
    /*private helper method for "printPreorderToConsole" method*/
    private void printPreorderToConsole(Node currentNode) {
        if (currentNode != null) {
            System.out.print(currentNode.getKey().toString()+" | ");
            printPreorderToConsole(currentNode.getLeft());
            printPreorderToConsole(currentNode.getRight());
        }
    }
    
    //------------------------------------------------------------------------------------------//
    /*Prints the tree in Postorder (left child, right child, parent)
    NOTES: uses a private helper method.*/
    public void printPostorderToConsole() {
        System.out.println("Postorder: ");
        printPostorderToConsole(root);
        System.out.println();
    }
    /*private helper method for "printPostorderToConsole" method*/
    private void printPostorderToConsole(Node currentNode) {
        if (currentNode != null) {
            printPostorderToConsole(currentNode.getLeft());
            printPostorderToConsole(currentNode.getRight());
            System.out.print(currentNode.getKey().toString()+" | ");
        }
    }
    
    //------------------------------------------------------------------------------------------//
    /*Returns smallest key within the tree*/
    public E minValue() {
        if (root == null) return null;
        return (E)(minNode(root).getKey());
    }
    /*Returns Node containing the smallest key starting from the given root*/
    private Node minNode(Node currentNode) {
        while (currentNode.getLeft() != null) {
            currentNode = currentNode.getLeft();
        }
        return currentNode;
    }
    
    //------------------------------------------------------------------------------------------//
}