/* 
 *  MainProgram
 *  
 *  version: 0.0.1
 *  
 *  data: September, 2014
 *  
 *  author: rbonifacio@cic.unb.br
 */
package br.unb.cic.iris.interpreter;

import java.util.Scanner;

import br.unb.cic.iris.interpreter.command.CommandManager;
import br.unb.cic.iris.interpreter.i18n.Message;

public class MainProgram {
	
	Scanner sc;
	
	public MainProgram() {
		sc = new Scanner(System.in);
	}
	public static void main(String args[]) {
		MainProgram m = new MainProgram();
		m.mainMenu();
		m.readCommand();
	}
	
	private void mainMenu() {
		System.out.println(Message.instance().getMessage("interpreter"));
		System.out.println(Message.instance().getMessage("version"));
		
		System.out.println(Message.instance().getMessage("help"));
	}
	
	private void readCommand() {
		try {
			System.out.print(Message.instance().getMessage("prompt"));
			String cmd = sc.nextLine();
			CommandManager.singleton().runCommand(cmd);
		}
		catch(RuntimeException e) {
			System.out.println("Erro! " + e.getMessage());
			
		}
		readCommand();
	}
	
	
}
