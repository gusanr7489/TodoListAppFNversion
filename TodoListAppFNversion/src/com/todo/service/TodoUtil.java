package com.todo.service;

import java.util.*;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		//ī�װ�, ����, ����, ������ �Է� ����
		System.out.print("<�׸� �߰�>\n"
				+ "ī�װ� -> ");
		
		category = sc.next();
		sc.nextLine();
		
		System.out.print("���� -> ");
		title = sc.next().trim();	
		
			// ���� �ߺ� Ȯ��
			if (list.isDuplicate(title)) {
				System.out.println("�׸� ������ �ߺ��˴ϴ�!");
				return;
			} 
			
		sc.nextLine();
		System.out.print("���� -> ");
		desc = sc.nextLine().trim();
		
		System.out.print("������ -> ");
		due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(list.addItem(t)>0)
			System.out.println("�߰��Ǿ����ϴ�.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "<�׸� ����>\n"
				+ "������ �׸��� ��ȣ�� �Է��ϼ��� -> ");
		int index = sc.nextInt();
		
/*		if(target==null) {
			System.out.println("�׸��� ã�� ���� �����ϴ�.");
			return;
		}*/
		
//		System.out.println(index + ". " + target.toString());
//		System.out.print("������ �����Ͻðڽ��ϱ�? (y/n) -> ");
//		String do_delete = sc.next();
		
		if(l.deleteItem(index)>0) {
			System.out.println("�����Ǿ����ϴ�.");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("<�׸� ����>\n"
				+ "������ �׸��� ��ȣ�� �Է��ϼ��� -> ");
		int index = sc.nextInt();
		
		System.out.print("�� ī�װ�? -> ");
		String new_category = sc.next().trim();
		
		System.out.print("�� ����? -> ");
		String new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title, index)) {
			System.out.println("�׸� ������ �ߺ��˴ϴ�!");
			return;
		}
		sc.nextLine();
		
		System.out.print("�� ����? -> ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("�� ������? -> ");
		String new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
		t.setId(index);
		if(l.updateItem(t)>0)
			System.out.println("�׸��� �����Ǿ����ϴ�.");
	}
	
	public static void find(String target, TodoList l) {
		int count=0;
		for(TodoItem myitem : l.getList(target)) {
			System.out.println(myitem.toString());
			count++;
		}
		System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
	}
	
	public static void findCategory(String target, TodoList l) {
		int count=0;
		for(TodoItem item : l.getListCategory(target)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
	}
	
	public static void listCategory(TodoList l) {
		int count=0;
		for(String item: l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.println("\n" + "�� " + count + "���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.");
	}

	public static void listAll(TodoList l) {
		System.out.println("<��ü ���, �� " + l.getCount() + "��>");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, int order) {
		int count=0;
		for (TodoItem item : l.getList()) {
			if(item.getIs_completed()==order) {
				System.out.println(item.toString());
				count++;
			}
		}
		System.out.println("�� " + count +"���� �׸��� �Ϸ�Ǿ����ϴ�.");
	}
	
	public static void listAll(TodoList l, String orderBy, int ordering) {
		System.out.println("<��ü ���, �� " + l.getCount() + "��>");
		for (TodoItem item : l.getOrderedList(orderBy, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void completeItem(TodoList l, int index) {
		if(l.setCompleted(index)>0) 
			System.out.println("�Ϸ� �����Ǿ����ϴ�.");
	}
	
}
