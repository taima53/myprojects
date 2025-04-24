package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	static DoubleLinkedList<District> districtDoubleLinkedList;
    public static void main(String[] args) throws FileNotFoundException {
         districtDoubleLinkedList = new DoubleLinkedList<>();
        
        File file = new File("myFile");
        Scanner input = new Scanner(file);
        while (input.hasNext()) {
            String bigName = input.nextLine();
            String[] list = bigName.split(",");
            if(list[2].length() != 0) {
            String name = list[0];
            String date = list[1];
            int age = Integer.parseInt(list[2]);
            String location = list[3];
            String district = list[4];
            String gender = list[5];
           District districtobj = new District(district);
            /*if district is not exist*/
            if (districtDoubleLinkedList.search(districtobj) == null) {
                districtDoubleLinkedList.insert(districtobj);
                Location locationobj = new Location(location);
             if(districtobj.getLocationLinkedList().search(locationobj) == null){
                 districtobj.getLocationLinkedList().insert(locationobj);
                Martyr martyr = new Martyr(name, date, age, location, district, gender);
               // if(locationobj.getMartyrLinkedList().search(martyr) == null){
                  locationobj.getMartyrLinkedList().insert(martyr);
                 //   locationobj.getMartyrLinkedList().traverse();
                
             }else{
            	 Node<Location> l = districtobj.getLocationLinkedList().search(locationobj);
                 Martyr martyr = new Martyr(name, date, age, location, district, gender);
                 //if(l.getData().getMartyrLinkedList().search(martyr) == null){
                    l.getData().getMartyrLinkedList().insert(martyr);
                     //locationobj.getMartyrLinkedList().traverse();
                 
             }
            }else{
            	DNode<District> d = districtDoubleLinkedList.search(districtobj);
                Location locationobj = new Location(location);
                if(d.getData().getLocationLinkedList().search(locationobj) == null){
                    d.getData().getLocationLinkedList().insert(locationobj);
                    Martyr martyr = new Martyr(name, date, age, location, district, gender);
                   // if(locationobj.getMartyrLinkedList().search(martyr) == null){
                        locationobj.getMartyrLinkedList().insert(martyr);
                       // locationobj.getMartyrLinkedList().traverse();
                    
                }else{
                	Node<Location> l =  d.getData().getLocationLinkedList().search(locationobj);
                    Martyr martyr = new Martyr(name, date, age, location, district, gender);
                   // if(l.getData().getMartyrLinkedList().search(martyr) == null){
                        l.getData().getMartyrLinkedList().insert(martyr);
                       // locationobj.getMartyrLinkedList().traverse();
                    
                }
            }


            } 
        }
        input.close();
        System.out.println(date("03/30/2018"));
        districtDoubleLinkedList.traverse();
     
    }
    public static int date(String date) {
    	int counter = 0;
			DNode<District> districtNode = districtDoubleLinkedList.dummyHead.getNext();
			
			while (districtNode != null) {
				//System.out.println(districtNode.getData()+"\n------------");
				Node<Location> locationNode = districtNode.getData().getLocationLinkedList().dummyHead.getNext();
				while (locationNode != null) {
					//System.out.println(locationNode.getData());
					Node<Martyr> martyrNode = locationNode.getData().getMartyrLinkedList().dummyHead.getNext();
					//System.out.println(martyrNode.getData());
					while (martyrNode != null) {
						//System.out.println(martyrNode.getData().getDate());
						if (martyrNode.getData().getDate().equals(date))
							counter++;
						martyrNode = martyrNode.getNext();
					}
					locationNode = locationNode.getNext();
				}
				districtNode = districtNode.getNext();
			}
			
			
		return counter;
    }
    }	
    

