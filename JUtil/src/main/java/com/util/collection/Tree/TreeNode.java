package com.util.collection.Tree;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author wulang
 * @version v1.0
 * @date 2017年11月24日 15:51
 * @description
 * @modified By:
 * @modifued reason:
 */
public class TreeNode<T> {
    private TreeNode<T> parentTreeNode;
    private List<TreeNode<T>> childNodes;
    private T node;

    public TreeNode() {
        this.parentTreeNode = null;
        this.childNodes = Lists.newArrayList();
    }

    public TreeNode(TreeNode<T> parentTreeNode, List<TreeNode<T>> childNodes) {
        this.parentTreeNode = parentTreeNode;
        this.childNodes = childNodes;
    }

    public TreeNode<T> getParentTreeNode() {
        return parentTreeNode;
    }

    public void setParentTreeNode(TreeNode<T> parentTreeNode) {
        this.parentTreeNode = parentTreeNode;
    }

    public List<TreeNode<T>> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<TreeNode<T>> childNodes) {
        this.childNodes = childNodes;
    }

    public T getNode() {
        return node;
    }

    public void setNode(T node) {
        this.node = node;
    }

    public TreeNode<T> appendChild(TreeNode<T> treeNode) {
        childNodes.add(treeNode);
        return this;
    }

}
