package com.xm.designpattern.behavior.command;

/**
 * 调用者即请求发送者，它通过命令对象来执行请求。一个调用者并不需要在设计时确定其接收者，因此它只与命令类之间存在关联关系。
 *
 * @author XM
 * @date 2023/1/11
 */	
public class RemoteControl {

	/**
	 * 可以是一个命令, 也可以是命令列表, 与具体命令实现解耦, 只关心调用命令
	 */
	private final Command command;

	public RemoteControl(Command command) {
		this.command=command;
	}
	
	public void action() {
		command.execute();
	}
	
	public void undo(){
		command.undo();
	}
}