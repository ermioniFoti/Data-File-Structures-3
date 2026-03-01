package project3_2020030081;

import java.util.List;

/**
 * A B+ tree
 * Since the structures and behaviors between internal node and external node are different, 
 * so there are two different classes for each kind of node.
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
public class BPlusTree<TKey extends Comparable<TKey>, TValue> {
    private BTreeNode<TKey> root;
   
    
    public BPlusTree() {
        this.root = new BTreeLeafNode<TKey, TValue>();
    }
    
    

    /**
     * Insert a new key and its associated value into the B+ tree.
     */
    public void insert(TKey key, TValue value) {
        BTreeLeafNode<TKey, TValue> leaf = this.findLeafNodeShouldContainKey(key);
        leaf.insertKey(key, value);
        
        if (leaf.isOverflow()) {
            BTreeNode<TKey> n = leaf.dealOverflow();
            if (n != null)
                this.root = n; 
        }
    }
    
    /**
     * Search a key value on the tree and return its associated value.
     */
    public List<TValue> search(TKey key) {
        BTreeLeafNode<TKey, TValue> leaf = this.findLeafNodeShouldContainKey(key);
        
        int index = leaf.search(key);
        return (index == -1) ? null : leaf.getValue(index);
    }
    
    /**
     * Delete a key and its associated value from the tree.
     */
    public void delete(TKey key) {
        BTreeLeafNode<TKey, TValue> leaf = this.findLeafNodeShouldContainKey(key);
        
        if (leaf.delete(key) && leaf.isUnderflow()) {
            BTreeNode<TKey> n = leaf.dealUnderflow();
            if (n != null)
                this.root = n; 
        }
    }
    
    /**
     * Search the leaf node which should contain the specified key
     */
    @SuppressWarnings("unchecked")
    private BTreeLeafNode<TKey, TValue> findLeafNodeShouldContainKey(TKey key) {
        BTreeNode<TKey> node = this.root;
        SearchStats.increaseNodeAccessCount();
        while (node.getNodeType() == TreeNodeType.InnerNode) {
            node = ((BTreeInnerNode<TKey>)node).getChild( node.search(key) );
            SearchStats.increaseNodeAccessCount();
        }
        
        return (BTreeLeafNode<TKey, TValue>)node;
    }
}
