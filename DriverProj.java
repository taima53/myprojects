package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DriverProj extends Application {
	private Button btLoad, btDistrictScreen, btInsert, btUpdate, btDelete, btPrev, btNext, btTotal, btNumberOfM,
			btNumberOfF, btAvgAge, btDate, btStatistic, btMaxDate, btLocationscreen, btInsertL, btUpdateL, btDeleteL,
			btNextL, btStatisticL, btTotalL, btNumberOfML, btNumberOfFL, btAvgAgesL, btYoungest, btOldest, btInsertM,
			btUpdateM, btDeleteM, btFindM;
	private TextField tfInsert, tfUpdateOld, tfUpdateNew, tfDelete, tfTotal, tfNumberOfM, tfNumberOfF, tfAvgAge,
			tfDateGiven, tfDateResult, tfDate, tfInsertL, tfUpdateLOld, tfUpdateLNew, tfDeleteL, tfTotalL, tfNumberOfML,
			tfNumberOfFL, tfAvgagesL, tfYoungest, tfOldest, tfInsertM, tfUpdateMOld, tfUpdateMNew, tfDeleteM, tfFindM;
	private VBox firstVbox;
	private HBox hbButtons, hbtextfields, hbInsert, hbDelete, hbDate, hbInsertL, hbUpdateL, hbDeleteL, hbYoungest,
			hbOldest, hbButtonsL, hbInsertM, hbDeleteM, hbUpdateM, hbFindM, hbRadio;
	private GridPane gridPane, gridPaneLoc;
	private RadioButton rbName, rbDate, rbAge, rbLocation, rbDistrict, rbGender;
	private Label lbResult, lbResultDis, lbResultLoc, lbResultMart;
	private ToggleGroup group;
	private FileChooser fileChooser;
	private File selectFile, file;
	private DoubleLinkedList<District> districtDoubleLinkedList;
	private Scene scene, sceneDistrict, sceneLoc;
	private Stage districtStage, locationStage;
	private DNode<District> curr;
	private Node<Location> currLocation, currentL;

	@Override
	public void start(Stage primaryStage) throws Exception {
		fileChooser = new FileChooser();
		districtDoubleLinkedList = new DoubleLinkedList<>();
		curr = districtDoubleLinkedList.dummyHead;

		lbResult = new Label(); // Empty Label

		/* initialize buttons in first scene */
		btLoad = new Button("Load");
		btDistrictScreen = new Button("District Screen");
		/* initialize vbox */
		firstVbox = new VBox();
		firstVbox.setAlignment(Pos.CENTER);
		firstVbox.setSpacing(10.0);
		firstVbox.getChildren().addAll(btLoad, btDistrictScreen, lbResult);

		/* set Load button on action */
		btLoad.setOnAction(e -> {
			fileChooser.setTitle("Open File");
			fileChooser.setInitialDirectory(new File("C:\\"));
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("csv", "*.csv"));
			selectFile = fileChooser.showOpenDialog(primaryStage);
			file = new File(selectFile.getPath());
			try {
				read();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			lbResult.setText("\tThe information has been uploaded \n\t\t\tsuccessfully.!");

		});

		/* Second scene */
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(5);
		gridPane.setVgap(5);

		tfInsert = new TextField();
		tfInsert.setPromptText("districtt name");
		btInsert = new Button("Insert");
		hbInsert = new HBox();
		hbInsert.getChildren().add(tfInsert);
		gridPane.add(hbInsert, 0, 0);
		gridPane.add(btInsert, 1, 0);
		tfInsert.setPrefColumnCount(15);

		tfUpdateNew = new TextField();
		tfUpdateOld = new TextField();
		btUpdate = new Button("Update");
		hbtextfields = new HBox(5);
		hbtextfields.getChildren().addAll(tfUpdateOld, tfUpdateNew);
		gridPane.add(hbtextfields, 0, 1);
		gridPane.add(btUpdate, 1, 1);
		tfUpdateNew.setPrefColumnCount(9);
		tfUpdateOld.setPrefColumnCount(8);
		tfUpdateNew.setPromptText("new name");
		tfUpdateOld.setPromptText("old name");

		tfDelete = new TextField();
		btDelete = new Button("Delete");
		hbDelete = new HBox();
		hbDelete.getChildren().add(tfDelete);
		gridPane.add(hbDelete, 0, 2);
		gridPane.add(btDelete, 1, 2);
		tfDelete.setPrefColumnCount(15);
		tfDelete.setPromptText("district name");

		btPrev = new Button("Previous");
		btNext = new Button("Next");
		lbResultDis = new Label(); // Empty label
		hbButtons = new HBox(60);
		hbButtons.getChildren().addAll(btPrev, lbResultDis, btNext);
		gridPane.add(hbButtons, 0, 3);

		btStatistic = new Button("Statistic");
		gridPane.add(btStatistic, 0, 4);
		gridPane.setHalignment(btStatistic, HPos.CENTER);
		tfTotal = new TextField();
		tfTotal.setPrefColumnCount(2);
		btTotal = new Button("Total # of martyrs");
		gridPane.add(btTotal, 0, 5);
		gridPane.add(tfTotal, 1, 5);

		tfNumberOfM = new TextField();
		tfNumberOfM.setPrefColumnCount(2);
		btNumberOfM = new Button("# of M");
		gridPane.add(btNumberOfM, 0, 6);
		gridPane.add(tfNumberOfM, 1, 6);

		tfNumberOfF = new TextField();
		tfNumberOfF.setPrefColumnCount(2);
		btNumberOfF = new Button("# of F");
		gridPane.add(btNumberOfF, 0, 7);
		gridPane.add(tfNumberOfF, 1, 7);

		tfAvgAge = new TextField();
		tfAvgAge.setPrefColumnCount(4);
		btAvgAge = new Button("Average of ages");
		gridPane.add(btAvgAge, 0, 8);
		gridPane.add(tfAvgAge, 1, 8);

		tfDate = new TextField();
		btMaxDate = new Button("maximum date");
		gridPane.add(btMaxDate, 0, 9);
		gridPane.add(tfDate, 1, 9);
		tfDate.setPrefColumnCount(4);

		tfDateGiven = new TextField();
		tfDateResult = new TextField();
		tfDateGiven.setPrefColumnCount(8);
		tfDateResult.setPrefColumnCount(8);
		tfDateGiven.setPromptText("Enter Date");
		tfDateResult.setPromptText("Result");
		hbDate = new HBox(5);
		hbDate.getChildren().addAll(tfDateGiven, tfDateResult);
		btDate = new Button("Date of maximum # of martyrs");
		gridPane.add(hbDate, 0, 10);
		gridPane.add(btDate, 1, 10);

		btLocationscreen = new Button("Location Screen");
		gridPane.add(btLocationscreen, 0, 11);
		gridPane.setHalignment(btLocationscreen, HPos.CENTER);

		sceneDistrict = new Scene(gridPane, 625, 370);

		districtStage = new Stage();
		districtStage.setTitle("District Screen");
		districtStage.setScene(sceneDistrict);
		districtStage.setResizable(false);

		btDistrictScreen.setOnAction(e -> {
			districtStage.show();
		});
		/* set insert button on action */
		btInsert.setOnAction(e -> {
			if (tfInsert.getText().isEmpty()) {
				lbResultDis.setText("try again!.");
			} else {
				District districtName = new District(tfInsert.getText());
				DNode<District> districtNameNode = districtDoubleLinkedList.search(districtName);
				if (districtDoubleLinkedList.search(districtName) == null) {
					districtDoubleLinkedList.insert(districtName);
					// districtDoubleLinkedList.traverse();
					lbResultDis.setText("added successfully!");
				} else {
					lbResultDis.setText("already exist!");
				}
			}

		});
		/* set update button on action */
		btUpdate.setOnAction(e -> {
			if (tfUpdateOld.getText().isEmpty() || tfUpdateNew.getText().isEmpty()) {
				lbResultDis.setText("try again!.");
			} else {
				String newName = tfUpdateNew.getText();
				String oldName = tfUpdateOld.getText();
				District oldDistrict = new District(oldName);
				DNode<District> Dnode = districtDoubleLinkedList.search(oldDistrict);
				if (Dnode != null) {
					Dnode.getData().setDistrictName(newName);
					districtDoubleLinkedList.delete(Dnode.getData());
					districtDoubleLinkedList.insert(Dnode.getData());
					// districtDoubleLinkedList.traverse();
					lbResultDis.setText("Updated successfully");
				} else
					lbResultDis.setText(oldName + " not found!.");
			}
		});

		/* set delete button on action */
		btDelete.setOnAction(e -> {
			if (tfDelete.getText().isEmpty()) {
				lbResult.setText("try again!. ");
			} else {
				String deleteName = tfDelete.getText();
				District deleteobj = new District(deleteName);
				if (districtDoubleLinkedList.search(deleteobj) != null) {
					districtDoubleLinkedList.delete(districtDoubleLinkedList.search(deleteobj).getData());
					lbResultDis.setText("Deleted successfully.");
				} else
					lbResultDis.setText(deleteName + " not found!.");

			}
		});

		/* set previous button on action */
		btPrev.setOnAction(e -> {
			if (curr != null && curr.getPrev() != districtDoubleLinkedList.dummyHead) {
				curr = curr.getPrev();
				lbResultDis.setText(curr.getData() + " ");
			} else {
				lbResultDis.setText(curr.getData() + " is First");

			}
		});

		/* set next button on action */
		btNext.setOnAction(e -> {
			if (curr != null && curr.getNext() != null) {
				curr = curr.getNext();
				lbResultDis.setText(curr.getData() + " ");
			} else {
				lbResultDis.setText(curr.getData() + " is Last.");
			}
		});
		/* set statistic button on action */
		btStatistic.setOnAction(e -> {
			if (curr.getData() != null) {
				District districtTotal = new District(curr.getData().getDistrictName());
				DNode<District> districtNode = districtDoubleLinkedList.search(districtTotal);
				int totalNumber = numberOfMartyr(districtNode.getData());
				tfTotal.setText(String.valueOf(totalNumber));
				int numberOfM = numberOfM(districtNode.getData());
				tfNumberOfM.setText(String.valueOf(numberOfM));
				int numberOfF = numberOfF(districtNode.getData());
				tfNumberOfF.setText(String.valueOf(numberOfF));
				double avgerageAge = avgAges(districtNode.getData());
				String averageAgeS = String.format("%.2f", avgerageAge);
				tfAvgAge.setText(averageAgeS);
				String date = maxDate(districtNode.getData());
				tfDate.setText(date);
			} else
				lbResultDis.setText("Select district.");

		});

		/* set date button on action */
		btDate.setOnAction(e -> {

			if (tfDateGiven.getText().isEmpty()) {
				lbResultDis.setText("try again.!");
			} else {
				int counter = 0;
				DNode<District> districtNode = districtDoubleLinkedList.dummyHead.getNext();
				while (districtNode != null) {
					Node<Location> locationNode = districtNode.getData().getLocationLinkedList().dummyHead.getNext();
					while (locationNode != null) {
						Node<Martyr> martyrNode = locationNode.getData().getMartyrLinkedList().dummyHead.getNext();
						while (martyrNode != null) {
							if (martyrNode.getData().getDate().equals(getDate(tfDateGiven.getText()))) {
								counter++;
							}
							martyrNode = martyrNode.getNext();
						}
						locationNode = locationNode.getNext();
					}
					districtNode = districtNode.getNext();
				}

				tfDateResult.setText(String.valueOf(counter));
			}
		});

		/* initialize buttons and TextField */
		gridPaneLoc = new GridPane();
		gridPaneLoc.setAlignment(Pos.CENTER);
		gridPaneLoc.setHgap(5);
		gridPaneLoc.setVgap(5);

		tfInsertL = new TextField();
		tfInsertL.setPromptText("Location name");
		tfInsertL.setPrefColumnCount(15);
		btInsertL = new Button("Insert");
		hbInsertL = new HBox(5);
		hbInsertL.getChildren().addAll(tfInsertL, btInsertL);
		gridPaneLoc.add(hbInsertL, 0, 0);

		tfUpdateLOld = new TextField();
		tfUpdateLOld.setPromptText("Old name");
		tfUpdateLOld.setPrefColumnCount(9);
		tfUpdateLNew = new TextField();
		tfUpdateLNew.setPromptText("New name");
		tfUpdateLNew.setPrefColumnCount(8);
		btUpdateL = new Button("Update");
		hbUpdateL = new HBox(5);
		hbUpdateL.getChildren().addAll(tfUpdateLOld, tfUpdateLNew, btUpdateL);
		gridPaneLoc.add(hbUpdateL, 0, 1);

		tfDeleteL = new TextField();
		btDeleteL = new Button("Delete");
		tfDeleteL.setPromptText("Location name");
		tfDeleteL.setPrefColumnCount(15);
		hbDeleteL = new HBox(5);
		hbDeleteL.getChildren().addAll(tfDeleteL, btDeleteL);
		gridPaneLoc.add(hbDeleteL, 0, 2);

		lbResultLoc = new Label("");
		btNextL = new Button("Next");
		hbButtonsL = new HBox(10);
		hbButtonsL.getChildren().addAll(lbResultLoc, btNextL);
		gridPaneLoc.add(hbButtonsL, 0, 3);
		gridPane.setHalignment(hbButtonsL, HPos.CENTER);

		btStatisticL = new Button("Statistic");
		gridPaneLoc.add(btStatisticL, 0, 4);
		gridPaneLoc.setHalignment(btStatisticL, HPos.CENTER);

		btTotalL = new Button("Total # of martyr");
		tfTotalL = new TextField();
		tfTotalL.setPrefColumnCount(2);
		gridPaneLoc.add(btTotalL, 0, 5);
		gridPaneLoc.add(tfTotalL, 1, 5);

		btNumberOfML = new Button("# of M");
		tfNumberOfML = new TextField();
		tfNumberOfML.setPrefColumnCount(2);
		gridPaneLoc.add(btNumberOfML, 0, 6);
		gridPaneLoc.add(tfNumberOfML, 1, 6);

		btNumberOfFL = new Button("# of F");
		tfNumberOfFL = new TextField();
		tfNumberOfFL.setPrefColumnCount(2);
		gridPaneLoc.add(btNumberOfFL, 0, 7);
		gridPaneLoc.add(tfNumberOfFL, 1, 7);

		btAvgAgesL = new Button("Average of ages");
		tfAvgagesL = new TextField();
		tfAvgagesL.setPrefColumnCount(3);
		gridPaneLoc.add(btAvgAgesL, 0, 8);
		gridPaneLoc.add(tfAvgagesL, 1, 8);

		btYoungest = new Button("Youngest Martyr");
		tfYoungest = new TextField();
		tfYoungest.setPrefColumnCount(20);
		hbYoungest = new HBox(20);
		hbYoungest.getChildren().addAll(btYoungest, tfYoungest);
		gridPaneLoc.add(hbYoungest, 0, 9);
		gridPaneLoc.setHalignment(hbYoungest, HPos.CENTER);

		btOldest = new Button("Oldest Martyr");
		tfOldest = new TextField();
		tfOldest.setPrefColumnCount(20);
		hbOldest = new HBox(35);
		hbOldest.getChildren().addAll(btOldest, tfOldest);
		gridPaneLoc.add(hbOldest, 0, 10);
		gridPaneLoc.setHalignment(hbOldest, HPos.CENTER);

		tfInsertM = new TextField();
		tfInsertM.setPromptText("name,date,age,location,district,gender");
		tfInsertM.setPrefColumnCount(20);
		btInsertM = new Button("Insert");
		hbInsertM = new HBox(5);
		hbInsertM.getChildren().addAll(tfInsertM, btInsertM);
		gridPaneLoc.add(hbInsertM, 0, 11);

		tfUpdateMOld = new TextField();
		tfUpdateMOld.setPromptText("Enter all data");
		tfUpdateMOld.setPrefColumnCount(18);
		tfUpdateMNew = new TextField();
		tfUpdateMNew.setPrefColumnCount(18);
		tfUpdateMNew.setPromptText("Enter all data");
		btUpdateM = new Button("Update");
		hbUpdateM = new HBox(15);
		hbUpdateM.getChildren().addAll(tfUpdateMOld, tfUpdateMNew, btUpdateM);
		gridPaneLoc.add(hbUpdateM, 0, 12);

		tfDeleteM = new TextField();
		btDeleteM = new Button("Delete");
		tfDeleteM.setPromptText("name,date,age,location,district,gender");
		tfDeleteM.setPrefColumnCount(15);
		hbDeleteM = new HBox(5);
		hbDeleteM.getChildren().addAll(tfDeleteM, btDeleteM);
		gridPaneLoc.add(hbDeleteM, 0, 13);

		tfFindM = new TextField();
		tfFindM.setPrefColumnCount(10);
		tfFindM.setPromptText("search by part of name");
		btFindM = new Button("Search");
		hbFindM = new HBox(5);
		hbFindM.getChildren().addAll(tfFindM, btFindM);
		gridPaneLoc.add(hbFindM, 0, 14);

		lbResultMart = new Label();
		gridPaneLoc.add(lbResultMart, 0, 17);
		gridPaneLoc.setHalignment(lbResultMart, HPos.CENTER);
		sceneLoc = new Scene(gridPaneLoc, 620, 550);

		locationStage = new Stage();
		locationStage.setTitle("Location Screen");
		locationStage.setResizable(false);
		locationStage.setScene(sceneLoc);

		/* set Insert Button Location on action */
		btInsertL.setOnAction(e -> {
			if (tfInsertL.getText().isEmpty())
				lbResultLoc.setText("try again.!");
			else {
				Location locationObj = new Location(tfInsertL.getText());
				if (curr.getData().getLocationLinkedList().search(locationObj) == null) {
					curr.getData().getLocationLinkedList().insert(locationObj);
					lbResultLoc.setText("insert successfully");
				} else
					lbResultLoc.setText("already exist.");
			}
		});

		/* set Update Location Button on Action */
		btUpdateL.setOnAction(e -> {
			if (tfUpdateLOld.getText().isEmpty() || tfUpdateLNew.getText().isEmpty())
				lbResultLoc.setText("try again.!");
			else {
				String oldName = tfUpdateLOld.getText();
				String newName = tfUpdateLNew.getText();
				Location location = new Location(oldName);
				Node<Location> Lnode = curr.getData().getLocationLinkedList().search(location);
				if (Lnode != null) {
					Lnode.getData().setLocationName(newName);
					curr.getData().getLocationLinkedList().delete(Lnode.getData());
					curr.getData().getLocationLinkedList().insert(Lnode.getData());
					lbResultLoc.setText("Updated Successfully");
				} else
					lbResultLoc.setText(oldName + " not found");
			}
		});

		/* set Delete Location Button on Action */
		btDeleteL.setOnAction(e -> {
			if (tfDeleteL.getText().isEmpty())
				lbResultLoc.setText("try again.!");
			else {
				Location location = new Location(tfDeleteL.getText());
				if (curr.getData().getLocationLinkedList().search(location) != null) {
					curr.getData().getLocationLinkedList()
							.delete(curr.getData().getLocationLinkedList().search(location).getData());
					lbResultLoc.setText("Deleted Successfully");
				} else
					lbResultLoc.setText(tfDeleteL.getText() + " not found");
			}
		});
		/* set Next Location Button On Action */
		btNextL.setOnAction(e -> {
			if (currentL != null && currentL.getNext() != null) {
				currentL = currentL.getNext();
				lbResultLoc.setText(currentL.getData() + "");
			} else {
				currentL = curr.getData().getLocationLinkedList().dummyHead.getNext();
				lbResultLoc.setText(currentL.getData() + "");
			}
		});

		/* set Statistic Location Button on Action */
		btStatisticL.setOnAction(e -> {
			if (currentL != null) {
				Location locationTotal = new Location(currentL.getData().getLocationName());
				Node<Location> locationNode = curr.getData().getLocationLinkedList().search(locationTotal);
				int totalNumber = numberOfMartyrL(locationNode.getData());
				tfTotalL.setText(String.valueOf(totalNumber));
				int numberOfM = numberOfML(locationNode.getData());
				tfNumberOfML.setText(String.valueOf(numberOfM));
				int numberOfF = numberOfFL(locationNode.getData());
				tfNumberOfFL.setText(String.valueOf(numberOfF));
				double averageAges = avgAgesL(locationNode.getData());
				String averageAgesl = String.format("%.2f", averageAges);
				tfAvgagesL.setText(averageAgesl);
				String youngestName = youngestMartyr(locationNode.getData());
				tfYoungest.setText(youngestName);
				String oldestName = oldestMartyr(locationNode.getData());
				tfOldest.setText(oldestName);

			} else
				lbResultLoc.setText("Select Location");

		});

		/* set insert martyr button on action */
		btInsertM.setOnAction(e -> {
			if (tfInsertM.getText().isEmpty() || lbResultLoc.getText().isEmpty())
				lbResultMart.setText("try again.!");
			else {
				String bigMartyr = tfInsertM.getText();
				try {
					String[] list = bigMartyr.split(",");
					String name = list[0];
					String date = list[1];
					int age = Integer.parseInt(list[2]);
					String location = list[3];
					String district = list[4];
					String gender = list[5];
					Martyr martyr = new Martyr(name, date, age, location, district, gender);
					if (currentL.getData().getMartyrLinkedList().search(martyr) == null) {
						currentL.getData().getMartyrLinkedList().insert(martyr);
						lbResultMart.setText("insert successfully");
					} else
						lbResultMart.setText("already exist");

				} catch (java.lang.NumberFormatException ex) {
					lbResultMart.setText("try again.!");
				} catch (java.lang.ArrayIndexOutOfBoundsException ex) {
					lbResultMart.setText("try again.!");
				}
			}
		});

		/* set Update martyr location */
		btUpdateM.setOnAction(e -> {
			if (tfUpdateMOld.getText().isEmpty() || tfUpdateMNew.getText().isEmpty())
				lbResultMart.setText("try again.!");
			else {
				String bigMartyrO = tfUpdateMOld.getText();
				try {
					String[] listO = bigMartyrO.split(",");
					String nameO = listO[0];
					String dateO = listO[1];
					int ageO = Integer.parseInt(listO[2]);
					String locationO = listO[3];
					String districtO = listO[4];
					String genderO = listO[5];
					Martyr martyr = new Martyr(nameO, dateO, ageO, locationO, districtO, genderO);
					Node<Martyr> martyrNode = currentL.getData().getMartyrLinkedList().search(martyr);
					if (martyrNode != null) {
						String bigMartyr = tfUpdateMNew.getText();
						try {
							String[] list = bigMartyr.split(",");
							String name = list[0];
							String date = list[1];
							int age = Integer.parseInt(list[2]);
							String location = list[3];
							String district = list[4];
							String gender = list[5];
							martyrNode.getData().setName(name);
							martyrNode.getData().setDate(date);
							martyrNode.getData().setAge(age);
							martyrNode.getData().setLocation(location);
							martyrNode.getData().setDistrict(district);
							martyrNode.getData().setGender(gender);
							currentL.getData().getMartyrLinkedList().delete(martyr);
							currentL.getData().getMartyrLinkedList().insert(martyr);
							lbResultMart.setText("Updated successfully");

						} catch (java.lang.NumberFormatException ex) {
							lbResultMart.setText("try again.!");
						} catch (java.lang.ArrayIndexOutOfBoundsException ex) {
							lbResultMart.setText("try again.!");
						}
					} else
						lbResultMart.setText(martyr + "not found");
				} catch (java.lang.NumberFormatException ex) {
					lbResultMart.setText("try again.!");
				} catch (java.lang.ArrayIndexOutOfBoundsException ex) {
					lbResultMart.setText("try again.!");
				}
			}

		});

		/* set Delete martyr button on action */
		btDeleteM.setOnAction(e -> {
			if (tfDeleteM.getText().isEmpty())
				lbResultMart.setText("try again!.");
			else {
				String bigMartyr = tfDeleteM.getText();
				try {
					String[] list = bigMartyr.split(",");
					String name = list[0];
					String date = list[1];
					int age = Integer.parseInt(list[2]);
					String location = list[3];
					String district = list[4];
					String gender = list[5];
					Martyr martyrObj = new Martyr(name, date, age, location, district, gender);
					if (currentL.getData().getMartyrLinkedList().search(martyrObj) != null) {
						currentL.getData().getMartyrLinkedList()
								.delete(currentL.getData().getMartyrLinkedList().search(martyrObj).getData());
						lbResultMart.setText("Delete Successfully");
					} else
						lbResultMart.setText(martyrObj.getName() + " not found");
				} catch (java.lang.NumberFormatException ex) {
					lbResultMart.setText("try again.!");
				} catch (java.lang.ArrayIndexOutOfBoundsException ex) {
					lbResultMart.setText("try again.!");
				}
			}
		});

		/* set find martyr button on action */
		btFindM.setOnAction(e -> {
			if (tfFindM.getText().isEmpty())
				lbResultMart.setText("try again!. ");
			else {
				if (currentL != null) {
					Location locationTotal = new Location(currentL.getData().getLocationName());
					Node<Location> locationNode = curr.getData().getLocationLinkedList().search(locationTotal);
					Martyr martyr = new Martyr(tfFindM.getText());
					if (find(martyr, locationNode.getData()) != -1)
						lbResultMart.setText(tfFindM.getText() + " is found");
					else
						lbResultMart.setText(tfFindM.getText() + " not found");
				}
			}
		});
		/* set Location Screen button on action */
		btLocationscreen.setOnAction(e -> {
			if (curr.getData() != null) {
				currLocation = curr.getData().getLocationLinkedList().dummyHead.getNext();
				// lbResultLoc.setText(currLocation.getData()+"");
				locationStage.show();
			} else
				lbResultDis.setText("try again!.");

		});
		scene = new Scene(firstVbox, 350, 200);

		primaryStage.setTitle("Martyrs Data");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

	private void read() throws IOException {
		Scanner input = new Scanner(selectFile);
		while (input.hasNext()) {
			String bigName = input.nextLine();
			String[] list = bigName.split(",");
			if (list[2].length() != 0) {
				String name = list[0];
				String date = list[1];
				int age = Integer.parseInt(list[2]);
				String location = list[3];
				String district = list[4];
				String gender = list[5];
				District districtobj = new District(district);
				/* if district is not exist */
				if (districtDoubleLinkedList.search(districtobj) == null) {
					districtDoubleLinkedList.insert(districtobj);
					Location locationobj = new Location(location);
					if (districtobj.getLocationLinkedList().search(locationobj) == null) {
						districtobj.getLocationLinkedList().insert(locationobj);
						Martyr martyr = new Martyr(name, getDate(date), age, location, district, gender);
						locationobj.getMartyrLinkedList().insert(martyr);
					} else {
						Node<Location> l = districtobj.getLocationLinkedList().search(locationobj);
						Martyr martyr = new Martyr(name, getDate(date), age, location, district, gender);
						l.getData().getMartyrLinkedList().insert(martyr);
					}
				} else {
					DNode<District> d = districtDoubleLinkedList.search(districtobj);
					Location locationobj = new Location(location);
					if (d.getData().getLocationLinkedList().search(locationobj) == null) {
						d.getData().getLocationLinkedList().insert(locationobj);
						Martyr martyr = new Martyr(name, getDate(date), age, location, district, gender);
						locationobj.getMartyrLinkedList().insert(martyr);
					} else {
						Node<Location> l = d.getData().getLocationLinkedList().search(locationobj);
						Martyr martyr = new Martyr(name, getDate(date), age, location, district, gender);
						l.getData().getMartyrLinkedList().insert(martyr);
					}
				}
			}
		}
		input.close();
	}

	private int numberOfMartyr(District district) {
		int counter = 0;
		Node<Location> currLocation = district.getLocationLinkedList().dummyHead.getNext();
		while (currLocation != null) {
			Node<Martyr> currMartyr = currLocation.getData().getMartyrLinkedList().dummyHead.getNext();
			while (currMartyr != null) {
				counter++;
				currMartyr = currMartyr.getNext();
			}
			currLocation = currLocation.getNext();
		}
		return counter;

	}

	private int numberOfM(District district) {
		int counter = 0;
		Node<Location> currLocation = district.getLocationLinkedList().dummyHead.getNext();
		while (currLocation != null) {
			Node<Martyr> currMartyr = currLocation.getData().getMartyrLinkedList().dummyHead.getNext();
			while (currMartyr != null) {
				if (currMartyr.getData().getGender().equals("M"))
					counter++;
				currMartyr = currMartyr.getNext();
			}
			currLocation = currLocation.getNext();
		}
		return counter;
	}

	private int numberOfF(District district) {
		int counter = 0;
		Node<Location> currLocation = district.getLocationLinkedList().dummyHead.getNext();
		while (currLocation != null) {
			Node<Martyr> currMartyr = currLocation.getData().getMartyrLinkedList().dummyHead.getNext();
			while (currMartyr != null) {
				if (currMartyr.getData().getGender().equals("F"))
					counter++;
				currMartyr = currMartyr.getNext();
			}
			currLocation = currLocation.getNext();
		}
		return counter;
	}

	private double avgAges(District district) {
		double sum = 0.0;
		int counter = 0;
		Node<Location> currLocation = district.getLocationLinkedList().dummyHead.getNext();
		while (currLocation != null) {
			Node<Martyr> currMartyr = currLocation.getData().getMartyrLinkedList().dummyHead.getNext();
			while (currMartyr != null) {
				sum = sum + currMartyr.getData().getAge();
				counter++;
				currMartyr = currMartyr.getNext();
			}
			currLocation = currLocation.getNext();
		}
		return sum / counter;

	}

	private String maxDate(District district) {
		Node<Location> currentLocation = district.getLocationLinkedList().dummyHead.getNext();
		LinkedList<DateMartyrCount> dateMartyrCounts = new LinkedList<>();
		while (currentLocation != null) {
			Node<Martyr> currentMartyr = currentLocation.getData().getMartyrLinkedList().dummyHead.getNext();
			while (currentMartyr != null) {
				boolean found = false;
				for (DateMartyrCount martyrCount : dateMartyrCounts) {
					if (martyrCount.getDate().equals(currentMartyr.getData().getDate())) {
						int counter = martyrCount.getCount();
						martyrCount.setCount(counter + 1);
						found = true;
						break;
					}
				}
				if (!found) {
					dateMartyrCounts.add(new DateMartyrCount(currentMartyr.getData().getDate(), 1));
				}
				currentMartyr = currentMartyr.getNext();
			}
			currentLocation = currentLocation.getNext();
		}
		String maxDate = "";
		int maxCount = 0;
		for (DateMartyrCount martyrCount : dateMartyrCounts) {
			if (martyrCount.getCount() > maxCount) {
				maxCount = martyrCount.getCount();
				maxDate = martyrCount.getDate();
			}
		}

		return maxDate;
	}

	private int numberOfMartyrL(Location location) {
		int counter = 0;
		Node<Location> currentLocation = curr.getData().getLocationLinkedList().search(location);
		Node<Martyr> currentMartyr = currentLocation.getData().getMartyrLinkedList().dummyHead.getNext();
		while (currentMartyr != null) {
			counter++;
			currentMartyr = currentMartyr.getNext();
		}

		return counter;
	}

	private int numberOfML(Location location) {
		int counterM = 0;
		Node<Location> currentLocation = curr.getData().getLocationLinkedList().search(location);
		Node<Martyr> currentMartyr = currentLocation.getData().getMartyrLinkedList().dummyHead.getNext();
		while (currentMartyr != null) {
			if (currentMartyr.getData().getGender().equals("M"))
				counterM++;
			currentMartyr = currentMartyr.getNext();
		}
		return counterM;
	}

	private int numberOfFL(Location location) {
		int counterF = 0;
		Node<Location> currentLocation = curr.getData().getLocationLinkedList().search(location);
		Node<Martyr> currentMartyr = currentLocation.getData().getMartyrLinkedList().dummyHead.getNext();
		while (currentMartyr != null) {
			if (currentMartyr.getData().getGender().equals("F"))
				counterF++;
			currentMartyr = currentMartyr.getNext();
		}
		return counterF;
	}

	private double avgAgesL(Location location) {
		double sum = 0.0;
		int counter = 0;
		Node<Location> currentLocation = curr.getData().getLocationLinkedList().search(location);
		Node<Martyr> currentMartyr = currentLocation.getData().getMartyrLinkedList().dummyHead.getNext();
		while (currentMartyr != null) {
			sum = sum + currentMartyr.getData().getAge();
			counter++;
			currentMartyr = currentMartyr.getNext();
		}
		return sum / counter;
	}

	private String youngestMartyr(Location location) {
		String martyrName = "";
		Node<Location> currentLocation = curr.getData().getLocationLinkedList().search(location);
		Node<Martyr> currentMartyr = currentLocation.getData().getMartyrLinkedList().dummyHead.getNext();
		martyrName = currentMartyr.getData().getName();
		return martyrName;
	}

	private String oldestMartyr(Location location) {
		String martyrName = "";
		Node<Location> currentLocation = curr.getData().getLocationLinkedList().search(location);
		Node<Martyr> currentMartyr = currentLocation.getData().getMartyrLinkedList().dummyHead.getNext();
		for (; currentMartyr != null && currentMartyr.getNext() != null; currentMartyr = currentMartyr.getNext())
			;
		martyrName = currentMartyr.getData().getName();
		return martyrName;
	}

	private int find(Martyr martyr, Location location) {
		Node<Location> currentLocation = curr.getData().getLocationLinkedList().search(location);
		Node<Martyr> currentMartyr = currentLocation.getData().getMartyrLinkedList().dummyHead.getNext();
		while (currentMartyr != null) {
			if (currentMartyr.getData().getName().contains(martyr.getName()))
				return 1;
			currentMartyr = currentMartyr.getNext();
		}
		return -1;
	}

	private String getDate(String date) {
		String[] parts = date.split("/");
		String day = parts[0];
		String month = parts[1];
		if (day.length() == 1) {
			day = "0" + day;
		}
		if (month.length() == 1) {
			month = "0" + month;
		}
		String bigDate = day + "/" + month + "/" + parts[2];
		return bigDate;
	}

}
