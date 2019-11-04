package com.ccmc.jdbc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hhqiw
 */
public class ToJson {

    public void treeToJson(List<Tree> list) {
//        System.out.println(list.size());
//        for (Tree tree : list) {
//            System.out.println(tree.getId() + "_" + tree.getName() + "_" + tree.getPid() + "_" + tree.getChildren());
//        }
        List<Tree> treeList = new ArrayList<Tree>();
//        treeList = listToTree(list);// 调用函数，传入List<Tree>参数
        treeList = listGetStree(list);
//        treeList =toTree(list);
//        treeList = build(list);
        System.out.println("SUCCESS TO JSON.");
        try {
            writeFileContext(treeList,"treetojson.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("66");
    }

    /**
     * 将list按行写入到txt文件中
     * @param strings
     * @param path
     * @throws Exception
     */
    public static void writeFileContext(List<Tree>  strings, String path) throws Exception {
        File file = new File(path);
        //如果没有文件就创建
        if (!file.isFile()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (Tree l:strings){
            writer.write(l + "\r\n");
        }
        writer.close();
    }

    /**
     * 方法一：用递归的方法
     *
     * @param list
     * @return
     */
    private List<Tree> listToTree(List<Tree> list) {
        // 用递归找子节点
        List<Tree> treeList = new ArrayList<Tree>();
        for (Tree tree : list) {
            //先找到根节点
            if (tree.getPid() == 0) {
                treeList.add(findChildren(tree, list));
            }
        }
        return treeList;
    }

    private Tree findChildren(Tree tree, List<Tree> list) {
        for (Tree node : list) {
            if (node.getPid() == tree.getId()) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<Tree>());
                }
                tree.getChildren().add(findChildren(node, list));
            }
        }
        return tree;
    }

    /**
     * ：2019-08-29 星期四 17:31
     * 方法二：二次循环方法
     */
    private static List<Tree> listGetStree(List<Tree> list) {
        System.out.println("444");
        List<Tree> treeList = new ArrayList<Tree>();
        for (Tree tree : list) {
            //找到根
            if (tree.getPid() == 0) {
                treeList.add(tree);
            }
            //找到子
            //TODO 2019-09-03 星期二 11:47:47：这里可以有算法优化，查找子可以用其他的查找算法，循环查找太慢了
            for (Tree treeNode : list) {
                if (treeNode.getPid() == tree.getId()) {
                    if (tree.getChildren() == null) {
                        tree.setChildren(new ArrayList<Tree>());
                    }
                    tree.getChildren().add(treeNode);
                }
            }
        }
        System.out.println("55");
        return treeList;
    }

    /**
     * 2019-08-29 星期四 17:55:25
     * 方法三
     */
    private static List<Tree> toTree(List<Tree> list) {
        List<Tree> treeList = new ArrayList<Tree>();
        for (Tree tree : list) {
            if (tree.getPid() == 0) {
                treeList.add(tree);
            }
        }
        for (Tree tree : list) {
            toTreehildren(treeList, tree);
        }
        return treeList;
    }

    private static void toTreehildren(List<Tree> treeList, Tree tree) {
        for (Tree node : treeList) {
            if (tree.getPid() == node.getId()) {
                if (node.getChildren() == null) {
                    node.setChildren(new ArrayList<Tree>());
                }
                node.getChildren().add(tree);
            }
            if (node.getChildren() != null) {
                toTreehildren(node.getChildren(), tree);
            }
        }
    }

    /**
     * 2019-08-29 星期四 18:11:26
     * 方法四：两层循环实现建树
     */
    public static List<Tree> build(List<Tree> treeNodes) {
        List<Tree> trees = new ArrayList<Tree>();
        for (Tree treeNode : treeNodes) {
            if ("0".equals(treeNode.getPid())) {
                trees.add(treeNode);
            }
            for (Tree it : treeNodes) {
                if (it.getPid() == treeNode.getId()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<Tree>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }
}
