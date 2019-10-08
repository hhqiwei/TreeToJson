package com.ccmc.jdbc;

import java.util.ArrayList;
import java.util.List;

public class ToJson {

    public void treeToJson(List<Tree> list) {
        List<Tree> treeList = new ArrayList<Tree>();
//        treeList = listToTree(list);// 调用函数，传入List<Tree>参数
        treeList = listGetStree(list);
//        treeList =toTree(list);
//        treeList = build(list);
//        System.out.println("SUCCESS TO JSON.\n" + JSON.toJSONString(treeList));
        System.out.println("SUCCESS TO JSON.\n" + treeList);

//        for (Tree temp: list) {
//            System.out.println(temp.getId()+"\t"+temp.getName()+"\t"+temp.getPid());
//        }

        //将转换完的数据保存到本地文件中
//        BufferedWriter writer = null;
//
//        File file = new File("src/treetojson.json");
//
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        try {
//            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
//            /**
//             * 2019-09-02 星期一 17:19:19
//             * TODO 当数据量非常大的时候，下面的TOJSONSTRING()方法会循环调用，导致栈溢出StackOverflow异常。使用别的方法将list转为JSON数据。
//             */
//            writer.write(JSON.toJSONString(treeList));//将JSON对象转化为JSON字符
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (writer != null) {
//                    writer.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println("The json writer success,please open the file 'treetojson.json'!");
//        }
    }

    // 方法一：用递归的方法
    private List<Tree> listToTree(List<Tree> list) {
        // 用递归找子节点
        List<Tree> treeList = new ArrayList<Tree>();
        for (Tree tree : list) {
            if (tree.getPid() == 0) {//先找到根节点
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
