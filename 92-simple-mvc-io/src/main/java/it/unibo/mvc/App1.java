package it.unibo.mvc;

public class App1 {
    public App1(){
        Controller controller = new Controller(); 
        System.out.println(controller);
        SimpleGUI view1 = new SimpleGUI();
        SimpleGUIWithFileChooser view2 = new SimpleGUIWithFileChooser();  
        view1.setController(controller);
        view2.setController(controller);
        controller.addView(view1); 
        controller.addView(view2); 
        controller.show(); 
    }

    public static void main(String[] args) {
        App1 app = new App1(); 
    }
}
