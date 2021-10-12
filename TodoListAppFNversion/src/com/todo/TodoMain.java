package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
		
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		
		boolean quit = false;
		Menu.displaymenu();
		
		do {
			Menu.prompt();
			String choice = sc.next();
			
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
			
			case "comp":
				int index = sc.nextInt();
				TodoUtil.completeItem(l, index);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "find":
				String target = sc.nextLine().trim();
				TodoUtil.find(target, l);
				break;
				
			case "find_cate":
				String cate = sc.nextLine().trim();
				TodoUtil.findCategory(cate, l);
				break;
				
			case "ls_name_asc":
				System.out.println("��������� �����߽��ϴ�.");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				System.out.println("��������� �������߽��ϴ�.");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("��¥������ �����߽��ϴ�.");
				TodoUtil.listAll(l, "due_date", 1);
				break;
			
			case "ls_date_desc":
				System.out.println("��¥������ �������߽��ϴ�.");
				TodoUtil.listAll(l, "due_date", 0);
				break;	
				
			case "ls_cate":
				TodoUtil.listCategory(l);
				break;

			case "ls_comp":
				TodoUtil.listAll(l, 1);
				break;
				
			case "exit":
				quit = true;
				break;
			
			case "help":
				Menu.displaymenu();
				break;
				
			default:
				System.out.println("�ش��ϴ� ��ɾ �ƴմϴ�.");
				break;
			}
			
		} while (!quit);
		sc.close();
		System.out.println("���α׷� �����");
	}
	
}
