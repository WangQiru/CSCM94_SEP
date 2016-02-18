package util;

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

public class OperationParser {

	public TreeNode<String> parse(List<String[]> operationList){
		TreeNode<String> parsedTree=new TreeNode<String>("root");
		for(int i=0;i<operationList.size();i++){
			for(String[] operation : operationList) {
				if(operation[0].equals("shape")){
					//shape
				}
				else if(operation[0].equals("mix")){
					if(operation[1].equals("union")){						
						//union
					}
					if(operation[1].equals("intersection")){
						//intersection
					}
					if(operation[1].equals("difference")){
						//difference
					}					
				}
				else if(operation[0].equals("translate")){
					//translate
				}
				else if(operation[0].equals("rotate")){
					//rotate
				}
				else if(operation[0].equals("scale")){
					//scale
				}
			}
		}
		return parsedTree;		
	}

	public List<String[]> parse(TreeNode<String> parsedTree){
		List<String[]> operationList=new ArrayList<String[]>();
		TreeNode<String> root = parsedTree;
		for (TreeNode<String> node : root) {
			int level=node.getLevel();
			System.out.println(level+": "+node.data);
		}//iterating the tree
		return operationList;
	}
}
