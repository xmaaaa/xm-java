package com.xm.designpattern.behavior.memento;

/**
 * 备忘录模式（Memento Pattern）保存一个对象的某个状态，以便在适当的时候恢复对象。
 * <p>
 * 应用实例： 1、后悔药。 2、打游戏时的存档。 3、Windows 里的 ctrl + z。 4、IE 中的后退。 5、数据库的事务管理。
 * 优点： 1、给用户提供了一种可以恢复状态的机制，可以使用户能够比较方便地回到某个历史的状态。 2、实现了信息的封装，使得用户不需要关心状态的保存细节。
 * 缺点：消耗资源。如果类的成员变量过多，势必会占用比较大的资源，而且每一次保存都会消耗一定的内存。
 *
 * @author XM
 * @date 2023/1/12
 */
public class MementoTest {
    public static void main(String[] args) {

        // 创建对象
        GameRole gameRole = new GameRole();
        gameRole.setName("安琪拉");
        gameRole.setVit(100);
        gameRole.setDef(100);
        System.out.print("【战前状态】");
        gameRole.display();

        // 把当前状态保存到备忘录对象 Caretaker
        Caretaker caretaker = new Caretaker();
        caretaker.add(gameRole.createMemento());

        // 对象状态改变
        gameRole.setName("安琪拉");
        gameRole.setDef(50);
        gameRole.setVit(60);

        System.out.print("【战后状态】");
        gameRole.display();
        caretaker.add(gameRole.createMemento());

        // 备忘录对象恢复之前对象状态
        gameRole.recoverGameRoleFromMemento(caretaker.get(0));
        System.out.print("【恢复第一次状态】");
        gameRole.display();
        gameRole.recoverGameRoleFromMemento(caretaker.get(1));
        System.out.print("【恢复第二次状态】");
        gameRole.display();

    }
}

