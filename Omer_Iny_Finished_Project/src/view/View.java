package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.CompanyUI;
import controller.Controller;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Department;
import model.Employee;
import model.Preference;
import model.Role;


//Omer Iny - 206571739

public class View {
	
	private CompanyUI controller;
	VBox vbFirstMainButtons,vbLoadingProccess,vbDateAndTime,vbAddDepartment,vbAddRole,vbEmpty,vbAddEmployee,vbBaseSalary,
	vbBaseSalaryPercent,vbPerHour,vbSecondryMainButtons,vbMainButtons,vbUpateMonthlySales
	,vbSetEmployeesWorkHours,vbDepartmentWorkHours,vbEmployeesWorkHoursVBox,vbChangeRolePreference,vbAlgoExplane;
	HBox hbExitButton,hbDuckPhoto;
	HBox hbLoadFromFile,hbAutoLoad,hbDepartmentChoice;
	Button btAutoGenerete,btDontAutoGenerete,btLoadFromFile,btDontLoadFromFile;
	Button btExit , btPicOfDuck,btBackToMainScreen,btSetCompanyName,btWindowSetCompanyName;
	BorderPane bpMainScreen;
	Text TAutoGen,TLoadFromFile,TDepartmentName,TSpace,TSpace2,TDate,TMainScreen,TMonthlySales,TDepartmentNameWorkHours,TDepartmentMustSync;
	TextField TFDepartmentName;
	CheckBox cbMustSyncDepartmentWorkHours,cbCantChangeWorkHoursDepartment,cbCantChangeWorkHoursRole,cbChangeWHRoleCanChangeWorkHours,cbDepartmentChangePrefCanChangeWorkHours,cbDepartmentChangePrefMustSync;
	TextField TFMonthlySales,TFDepartmentStartHours;
	Button btAddDepartment,btConfirmAddDepartment,btAddRole,btConfirmAddRole;
	Button btInputInformation,btShowInformation,btChangeWorkHoursSyncRole,btChangeWorkHoursPreferenceDepartment,btShowProfit,btAddEmployee,
	btShowEmployeesProfit,btShowDepartmentProfit,btShowCompanyProfit,btUpdateCompanyMonthlySales,btShowAllDepartments,
	btShowAllRolesInDepartment,btShowCompanyData,btShowAllEmployees,btContinueEmployee,btConfirmAddEmployee,btConfirmChangeRolePreference,
	btEmployeeGoBack,btSetEmployeesWorkHours,btConfirmUpdateCompanyMonthlySales,btSetWorkHours,btConfirmChangeWorkHoursPreferenceDepartment,btAlgoExplane;
	VBox vbShowAllEmployees,vbShowAllDepartments,vbShowAllRolesInDepartment,vbShowCompanyData,vbChangeDepartmentPref,vbShowEmployeesProfit,vbShowDepartmentProfit,vbShowCompanyProfit;
	
	Text TEnterCompany;
	TextField TFCompanyName;
	VBox vbEnterCompanyName;
	
	Text TShowEmployeesProfit,TShowDepartmentProfit,TShowCompanyProfit,TChooseDepartment,TAlgoExplane;
	
	
	
	CheckBox cbWorkFromHome;
	
	
	TextField TFEmployeeName,TFEmployeeID,TFNameOfRole,TFBaseSalary,TFPercentFromAllSales,TFSalaryPerHour
	,TFWorkHoursPerMonth,TFStartWorkTimeEmployee,TFEndWorkTimeEmployee,TFStartWorkTime,TFEndWorkTime;
	Text TEmployeeName,TEmployeeID,TDateOfBirth,TSalaryPreferences,TPreferenceOfEmployee,TDepartment,TNameOfRole,
			TBaseSalary,TPercentFromAllSales,TSalaryPerHour,TRole,TInputInformation,TShowInformation,TShowProfit,TAddRoleToDepartment,
			TWarningOfOverRide,TWorkHoursPerMonth,TRoleWorkHours,TEmployeeNameWorkHours,TShowRolesInDepartment,TPreference,
			TCantChangeWorkHoursInDepartment,TStartWorkTime,TEndWorkTime,TRoleCantChangeHours,TChooseDepartmentShowRoles;
	DatePicker dpBDay;
	ComboBox<String> cobSalaryPreferences,cobRolesByDepartment;
	ComboBox<Preference> cobPreferenceEmployee,cobPreferenceDepartment;
	ComboBox<String> cobDepartmentRole,cobDepartmentEmployee,cobDepartmentsShowRoles,cobDepartmentChangeRolePref,cobRoleChangePref,
	cobDepartmentChangePref,cobDepartmentWorkHours,cobEmployee,cobDepartmentShowProfit;
	String SalaryPreference;
	ScrollPane spShowEmployees,spShowAllDepartments,spShowAllRolesInDepartment,spShowAllCompanyData,spShowEmployeesProfit,spShowDepartmentProfit,
	spShowCompanyProfit,spAlgoExplane;
	
	private final static ArrayList<Preference> preferences = new ArrayList<Preference>(Controller.getPreferencesArray());
	
	public void setListener(CompanyUI c) {
		this.controller = c;
	}
	
	
	
	
	/*
	 * TO DO
	 * QA
	 */
	
	public View(Stage primaryStage) throws Exception{

		bpMainScreen = new BorderPane();
		this.controller = null;
		TPreference = new Text("Preference");
		TAutoGen = new Text("Do You want to autoLoad DATA to the system\nIf You Choose to autoLoad it will ran over the current File That is saved");
		TLoadFromFile = new Text("Load From File");
		TDepartmentName = new Text("Department Name: ");
		TWarningOfOverRide = new Text("Please Notice!\nif the Role of Employee does not allow to change work hours\n"
				+ "or the Department must work in Sync or cant change work hour\nyou will not be able to set his work hours unless the "
				+ "\nrole's/department must sync and cant change work hours will change");
		
		
		TSpace = new Text("                                             ");
		TSpace2 = new Text("                                            ");
		TDate = new Text(LocalDate.now().toString());
		
		TInputInformation = new Text("Input Information Manu");
		TShowInformation = new Text("Show Information Manu");
		TShowProfit = new Text("Show Profit Manu");
		TMainScreen = new Text("This is the Main Screen");
		
		TAddRoleToDepartment = new Text("Please select the Department to add the Role to: ");
		TNameOfRole = new Text("Name Of Role: ");
		TChooseDepartmentShowRoles = new Text("Choose Department");
		TChooseDepartment = new Text("Choose Department");
		cbCantChangeWorkHoursRole = new CheckBox("All Workers In This Role Must Work 8:00 - 17:00");
		
		
		
		TCantChangeWorkHoursInDepartment = new Text("You Cant change Work Hours In this Department");
		TStartWorkTime = new Text("Start Work Time: ");
		TEndWorkTime = new Text("End Work Time: ");
		TRoleCantChangeHours = new Text("this Employee is in a role that cant change his work hours");
		TFStartWorkTime = new TextField();
		TFEndWorkTime = new TextField();
		cbWorkFromHome = new CheckBox("Work From Home");
		
		
		vbAlgoExplane = new VBox();

		vbShowEmployeesProfit = new VBox();
		vbShowDepartmentProfit = new VBox();
		vbShowCompanyProfit = new VBox();
		
		TShowEmployeesProfit = new Text("");
		cobDepartmentShowProfit = new ComboBox<String>();
		TShowDepartmentProfit = new Text("");
		TShowCompanyProfit = new Text("");
		/*
		 *  KEY GUILDLINES 
		 * 
		 * IF DEPARTMENT CANT CHANGE WORK HOURS ALL OF THE ROLES WILL CHANGE CHANGE WORK HOURS TO FALSE 
		 * IF DEPARTMENT MUST BE SYNCED ALL ROLES WILL CHANGE CHANGE WORK HOURS TO TRUE
		 * IF ROLE CHANGE WORK HOURS = FALSE THEN EMPLOYEE WORK HOURS WILL BE AUTOMATICLY 8:00-17:00 AND EFFITIUNCHI WILL NOT CHANGE
		 */
		
		TAlgoExplane = new Text(" KEY GUILDLINES FOR PROGRAM \r\n"
				+ "  \r\n"
				+ "  IF DEPARTMENT CANT CHANGE WORK HOURS ALL OF THE ROLES WILL \n"
				+ "CHANGE CHANGE WORK HOURS TO FALSE \r\n"
				+ "  IF DEPARTMENT MUST BE SYNCED ALL ROLES WILL CHANGE CHANGE\n WORK HOURS TO TRUE\r\n"
				+ "  IF ROLE CHANGE WORK HOURS = FALSE THEN EMPLOYEE WORK \nHOURS WILL BE AUTOMATICLY "
				+ "8:00-17:00  \nROLES ARE DEFINED BY THERE DEPARTMENT \n2 ROLES BY THE SAME NAME ARE NOT THE SAME ROLES IN \nDIFFERENT DEPARTMENTS\n\n\n"+"The Calculation Of Effitiency For Employees is done by this Logic:\n"
				+ "if An Employee Prefers to start working Earlier then Every Hour\n he works before 8:00 counts as an "
				+ "'EFFITIENT HOUR'\n"
				+ "Every Hour he works after 17:00 Counts as an 'NOT EFFITIENT HOUR'\n\n"
				+ "if An Employee Prefers to start working Later then Every Hour he works before 8:00\n"
				+ " counts as an 'NOT EFFITIENT HOUR'\n"
				+ "Every Hour he works after 17:00 Counts as an 'EFFITIENT HOUR' \n\nFor Preferece "
				+ "Stay The Same, Hours before 8 and after 17\nwill be counted as NOT EFFITENT HOURS.\n\nFor The Preference Work From Home "
				+ "the hours dont matter and only\nif the employee works from home his effitiency will "
				+ "increse.\n\n\nWhen You Add a Department if you select cant change work \nMust Sync will be set to true \n\n\nYou can autoLoad and Load from file if you wont,\na new Company will be set "
				+ "and the file contant will be Lost.\n\n\n\n\n\n\n\n\n\nBTW, there is a picture of a duck for your amuzment:) Just Press the button.");
		
		vbAlgoExplane.getChildren().add(TAlgoExplane);
		TEmployeeName = new Text("Name Of Employee: ");
		TEmployeeID = new Text("ID: ");
		TDateOfBirth = new Text("Date Of Birth: ");
		TSalaryPreferences = new Text("Salary Preferences: ");
		TPreferenceOfEmployee = new Text("Work Hours Preference: ");
		TDepartment = new Text("To Which Department do you want to add an Employee: ");
		TRole = new Text("Role: ");
		TBaseSalary = new Text("Base Salary: ");
		TPercentFromAllSales = new Text("Percent From All Sales: ");
		TSalaryPerHour = new Text("Salary Per Hour: ");
		TWorkHoursPerMonth = new Text("Work Hours Per Month");
		btContinueEmployee = new Button("Continue");
		
		
		
		TEnterCompany = new Text("Please Enter The Company Name");
		TFCompanyName = new TextField();
		btSetCompanyName = new Button("Set Company's Name");
		vbEnterCompanyName = new VBox();
		vbEnterCompanyName.getChildren().addAll(TEnterCompany,TFCompanyName,btSetCompanyName);
		
		
		cobDepartmentsShowRoles = new ComboBox<String>();
		cobRolesByDepartment = new ComboBox<String>();
		
		
		
		cbChangeWHRoleCanChangeWorkHours = new CheckBox("Mark If you Want this Role can not change work hours");
		cobDepartmentChangeRolePref = new ComboBox<String>();
		cobRoleChangePref = new ComboBox<String>();
		btConfirmChangeRolePreference = new Button("Change Preference");
		vbChangeRolePreference = new VBox();

		
		dpBDay = new DatePicker();
		
		spShowEmployees = new ScrollPane();
		spAlgoExplane = new ScrollPane();
		spShowAllDepartments = new ScrollPane();
		spShowAllRolesInDepartment = new ScrollPane();
		spShowAllCompanyData = new ScrollPane();
		spShowEmployeesProfit = new ScrollPane();
		spShowDepartmentProfit = new ScrollPane();
		spShowCompanyProfit = new ScrollPane();
		spAlgoExplane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		spShowEmployees.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		spShowAllDepartments.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		spShowAllRolesInDepartment.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		spShowAllCompanyData.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		spShowEmployeesProfit.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		spShowDepartmentProfit.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		spShowCompanyProfit.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		spShowEmployees.setMaxSize(580, 450);
		spAlgoExplane.setMaxSize(580,450);
		spShowAllDepartments.setMaxSize(580, 450);
		spShowAllRolesInDepartment.setMaxSize(580, 450);
		spShowAllCompanyData.setMaxSize(580, 450);
		spShowEmployeesProfit.setMaxSize(580, 450);
		spShowDepartmentProfit.setMaxSize(580, 450);
		spShowCompanyProfit.setMaxSize(580, 450);
		
		
		vbShowAllEmployees = new VBox();
		vbShowAllDepartments = new VBox();
		vbShowAllRolesInDepartment = new VBox();
		vbShowCompanyData = new VBox();
		spShowEmployees.setContent(vbShowAllEmployees);
		spShowAllDepartments.setContent(vbShowAllDepartments);
		spShowAllRolesInDepartment.setContent(vbShowAllRolesInDepartment);
		spShowAllCompanyData.setContent(vbShowCompanyData);
		spShowEmployeesProfit.setContent(vbShowEmployeesProfit);
		spShowDepartmentProfit.setContent(vbShowDepartmentProfit);
		spShowCompanyProfit.setContent(vbShowCompanyProfit);
		spAlgoExplane.setContent(vbAlgoExplane);
		
		
		
		
		
		TFDepartmentName = new TextField();
		TFNameOfRole = new TextField();
		TFEmployeeName = new TextField();
		TFEmployeeID = new TextField();
		TFBaseSalary = new TextField();
		TFPercentFromAllSales = new TextField();
		TFSalaryPerHour = new TextField();
		TFWorkHoursPerMonth = new TextField();

		
		
		
		TShowRolesInDepartment = new Text("Choose Department to show all Roles");
		TEmployeeNameWorkHours = new Text("Employee: ");
		TDepartmentMustSync = new Text("This Department Must Sync All Of The Employees will start in the Hour You Set");

	
		
		
		cobDepartmentChangePref = new ComboBox<String>();
		cbDepartmentChangePrefCanChangeWorkHours = new CheckBox("Cant Change Work Hours (08:00-17:00)");
		cbDepartmentChangePrefMustSync = new CheckBox("Must Sync Work Hours Of All Employees");
		btConfirmChangeWorkHoursPreferenceDepartment = new Button("Change Department Preference");
		vbChangeDepartmentPref = new VBox();
		
		
		
		cobSalaryPreferences = new ComboBox<String>();
		cobPreferenceEmployee = new ComboBox<Preference>();
		cobPreferenceDepartment = new ComboBox<Preference>();
		cobDepartmentRole = new ComboBox<String>();
		cobDepartmentWorkHours = new ComboBox<String>();
		cobDepartmentEmployee = new ComboBox<String>(); 
		cobEmployee = new ComboBox<String>(); 
		cobSalaryPreferences.getItems().addAll("Base Salary","Base Salary + Percentage of all sales","Salary Per Hour");
		cobPreferenceEmployee.getItems().addAll(Controller.getPreferencesArray());
		cobPreferenceDepartment.getItems().addAll(Controller.getPreferencesArray());
		//UPDATE DEPARTMENTS COB WHEN CLICKED ON ADD ROLE        ########
		//UPDATE SET VISIBEL DEPARTMENTS
		
		
		cbMustSyncDepartmentWorkHours = new CheckBox("All Workers Work Hours need to be Synced");
		cbCantChangeWorkHoursDepartment = new CheckBox("All Workers In Department Must Work 8:00 - 17:00");
		
		
		
		btAutoGenerete = new Button("Auto Generete");
		btLoadFromFile = new Button("Load From File");
		btDontLoadFromFile = new Button("Dont Load From File");
		btDontAutoGenerete = new Button("Dont Auto Generete");
		btWindowSetCompanyName = new Button("Set Company Name");
		
		
		btExit = new Button("Exit");
		btAlgoExplane = new Button("Algorithem And Code Explane");
		btPicOfDuck = new Button("To Show Picture of Duck");
		btBackToMainScreen = new Button("Go Back");
		
		btEmployeeGoBack = new Button("Go Back");
	
		btInputInformation = new Button("Input Information");
		btShowInformation = new Button("Show Information");
		btShowAllEmployees = new Button("Show All Employees");
		btShowAllDepartments = new Button("Show All Departments");
		btShowAllRolesInDepartment = new Button("Show All Roles In Department");
		btShowCompanyData = new Button("Show All Company Data");
		btChangeWorkHoursSyncRole = new Button("Change Role Work Hours Preference");
		btChangeWorkHoursPreferenceDepartment = new Button("Change Department Work Hours Preference");
		btShowProfit = new Button("Show Company Profit");
		btShowEmployeesProfit = new Button("Show Employees Profit");
		btShowDepartmentProfit = new Button("Show Department Profit");
		btShowCompanyProfit = new Button("Show Company Profit");
		btAddDepartment = new Button("Add Department");//MAIN BUTTONS
		btAddRole = new Button("Add Role to Department");
		btAddEmployee = new Button("Add Employee");
		btUpdateCompanyMonthlySales = new Button("Update Company Monthly Sales");
		btConfirmUpdateCompanyMonthlySales = new Button("Update Sales");
		btSetEmployeesWorkHours = new Button("Set Employees Work Hours");
		btSetWorkHours = new Button("Set Work Hours");
		
	
		
		
		btConfirmAddDepartment = new Button("Add Department");//CONFIRM BUTTONS
		btConfirmAddRole = new Button("Add Role To Department");
		btConfirmAddEmployee = new Button("Add Employee");
	    
	    vbDateAndTime = new VBox(TDate);
	    vbLoadingProccess =  new VBox();
	    vbAddDepartment =  new VBox();
	    vbAddRole =  new VBox();
	    vbEmpty =  new VBox();
	    vbAddEmployee =  new VBox();
	    vbBaseSalary =  new VBox();
	    vbBaseSalaryPercent =  new VBox();
	    vbPerHour =  new VBox();
	    vbFirstMainButtons = new VBox();
	    vbSecondryMainButtons = new VBox();
	    vbMainButtons = new VBox();
	    vbUpateMonthlySales = new VBox();
	    vbSetEmployeesWorkHours = new VBox();
	    vbDepartmentWorkHours = new VBox();
	    vbEmployeesWorkHoursVBox = new VBox();    
	    
	    TMonthlySales = new Text("Please enter this month's Sales");
	    TFMonthlySales = new TextField();
	    vbUpateMonthlySales.getChildren().addAll(TMonthlySales,TFMonthlySales,btConfirmUpdateCompanyMonthlySales);
	    
	    
	    
	    
	    
	    
	    hbDuckPhoto = new HBox();
	    
	    hbAutoLoad = new HBox();
	    hbLoadFromFile = new HBox();
	    hbExitButton = new HBox();
	    hbDepartmentChoice = new HBox();
		
	    
	    vbFirstMainButtons.getChildren().addAll(btInputInformation,btShowInformation,btChangeWorkHoursSyncRole,btChangeWorkHoursPreferenceDepartment,btShowProfit);
	    vbMainButtons.getChildren().addAll(vbFirstMainButtons,vbSecondryMainButtons);
	    vbMainButtons.setPadding(new Insets(40));
	    
		
	   
//#########DUCK SHIT###########################################################################################################################################	    
	    InputStream isDuck1 = null;
	    try {
	    	isDuck1 = new FileInputStream("duck.jpeg"); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	    
	    final ImageView duck1Image = new ImageView();  
        Image image1 = new Image(isDuck1);
        duck1Image.setImage(image1);
        
        
        final ImageView duck2Image = new ImageView();  
        
        InputStream isDuck2 = null;
	    try {
	    	isDuck2 = new FileInputStream("duck2.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	    Image image2 = new Image(isDuck2);
	    duck2Image.setImage(image2);
	    hbDuckPhoto.getChildren().addAll(duck1Image,duck2Image);
	    
//#########DUCK SHIT FINISH ###########################################################################################################################################	    

	    
	    
	    
	    
	    
		hbAutoLoad.getChildren().addAll(TAutoGen,btAutoGenerete,btDontAutoGenerete);
		hbAutoLoad.setPadding(new Insets(30));
		hbLoadFromFile.getChildren().addAll(TLoadFromFile,btLoadFromFile,btDontLoadFromFile);
		hbLoadFromFile.setPadding(new Insets(30));
		vbLoadingProccess.getChildren().add(hbAutoLoad);
		
		 
		
		
		vbAddDepartment.setPadding(new Insets(30));
		vbAddRole.setPadding(new Insets(30));
		vbAddRole.getChildren().addAll(TAddRoleToDepartment,cobDepartmentRole,TNameOfRole,TFNameOfRole,cbCantChangeWorkHoursRole,btConfirmAddRole);
		vbAddEmployee.setPadding(new Insets(30));
		vbAddEmployee.getChildren().addAll(TDepartment,cobDepartmentEmployee,btContinueEmployee,TWarningOfOverRide);	
		
		hbExitButton.setPadding(new Insets(30));
		vbBaseSalary.setPadding(new Insets(30));
	    vbBaseSalaryPercent.setPadding(new Insets(30));
	    vbPerHour.setPadding(new Insets(30));
	    vbSetEmployeesWorkHours.setPadding(new Insets(30));
		
		
		hbExitButton.getChildren().addAll(btExit,TSpace,btPicOfDuck,TSpace2,btAlgoExplane);
		
		
		
		bpMainScreen.setCenter(vbLoadingProccess);
		
		bpMainScreen.setRight(vbDateAndTime);
//BUTTONS SET ON ACTIONS****************************************************************************************************************************************
		
		
		
		
		btAlgoExplane.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				bpMainScreen.setCenter(spAlgoExplane);
			}
		});
		
		
		btShowEmployeesProfit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vbShowEmployeesProfit.getChildren().clear();
				TShowEmployeesProfit.setText(controller.getEmployeesProfit());
				vbShowEmployeesProfit.getChildren().add(TShowEmployeesProfit);
				bpMainScreen.setCenter(spShowEmployeesProfit);
			}
		});
		btShowCompanyProfit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vbShowCompanyProfit.getChildren().clear();
				TShowCompanyProfit.setText(controller.getCompanyProfit());
				vbShowCompanyProfit.getChildren().addAll(TShowCompanyProfit);
				bpMainScreen.setCenter(spShowCompanyProfit);
			}
		});
		btShowDepartmentProfit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vbShowDepartmentProfit.getChildren().clear();
				resetCobDepartment(cobDepartmentShowProfit);
				vbShowDepartmentProfit.getChildren().addAll(TChooseDepartment,cobDepartmentShowProfit);
				bpMainScreen.setCenter(spShowDepartmentProfit);
			}
		});
		
		cobDepartmentShowProfit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(cobDepartmentShowProfit.getValue()!=null) {
					TShowDepartmentProfit.setText(controller.getDepartmentProfit(cobDepartmentShowProfit.getValue()));
					vbShowDepartmentProfit.getChildren().clear();
					vbShowDepartmentProfit.getChildren().addAll(TChooseDepartment,cobDepartmentShowProfit,TShowDepartmentProfit);
				}
			}
		});
		
		
		
		
		cobDepartmentsShowRoles.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(cobDepartmentsShowRoles.getValue()!=null) {
					TShowRolesInDepartment.setText(controller.getStringRolesInDepartment(cobDepartmentsShowRoles.getValue()));
				}
			}
		});
		
	

		
		btSetEmployeesWorkHours.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vbSetEmployeesWorkHours.getChildren().clear();
				resetCobDepartment(cobDepartmentWorkHours);
				vbSetEmployeesWorkHours.getChildren().addAll(TDepartment,cobDepartmentWorkHours);
				bpMainScreen.setCenter(vbSetEmployeesWorkHours);
				
			}
		});
		
		
		
		cobDepartmentWorkHours.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(cobDepartmentWorkHours.getValue()!=null) {
					if(!controller.getDepartmentByName(cobDepartmentWorkHours.getValue()).getCanChangeWorkHours()) {
						vbSetEmployeesWorkHours.getChildren().clear();
						vbSetEmployeesWorkHours.getChildren().addAll(TDepartment,cobDepartmentWorkHours,TCantChangeWorkHoursInDepartment);
					}
					else if(controller.getDepartmentByName(cobDepartmentWorkHours.getValue()).getMustSync()) {
						vbSetEmployeesWorkHours.getChildren().clear();
						vbSetEmployeesWorkHours.getChildren().addAll(TDepartment,cobDepartmentWorkHours,TStartWorkTime,TFStartWorkTime,cbWorkFromHome,btSetWorkHours,TDepartmentMustSync);
					}
					else {
						vbSetEmployeesWorkHours.getChildren().clear();
						resetCobEmployee(cobDepartmentWorkHours.getValue(), cobEmployee);
						vbSetEmployeesWorkHours.getChildren().addAll(TDepartment,cobDepartmentWorkHours,TEmployeeNameWorkHours,cobEmployee);
					}
				}
					
			}
		});
		

		
		
		cobEmployee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(cobEmployee.getValue()!=null) {
					if(!controller.getEmployeeByName(cobDepartmentWorkHours.getValue(),cobEmployee.getValue()).getRole().getCanChangeWorkHours()) {
						vbSetEmployeesWorkHours.getChildren().clear();
						vbSetEmployeesWorkHours.getChildren().addAll(TDepartment,cobDepartmentWorkHours,TEmployeeNameWorkHours,cobEmployee,TRoleCantChangeHours);
					}
					else if(controller.isEmployeePerHour(cobDepartmentWorkHours.getValue(),cobEmployee.getValue())) {
						vbSetEmployeesWorkHours.getChildren().clear();
						vbSetEmployeesWorkHours.getChildren().addAll(TDepartment,cobDepartmentWorkHours,TEmployeeNameWorkHours,cobEmployee,TStartWorkTime
								,TFStartWorkTime,TEndWorkTime,TFEndWorkTime,cbWorkFromHome,btSetWorkHours);
					}
					else {
						vbSetEmployeesWorkHours.getChildren().clear();
						vbSetEmployeesWorkHours.getChildren().addAll(TDepartment,cobDepartmentWorkHours,TEmployeeNameWorkHours,cobEmployee,TStartWorkTime
								,TFStartWorkTime,cbWorkFromHome,btSetWorkHours);
					}
				}
			}
		});
		
		
		btSetWorkHours.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(cobDepartmentWorkHours.getValue()==null) {
					showMsg("Must Fill Alll Fields");
				}
				else {
					if(controller.getDepartmentByName(cobDepartmentWorkHours.getValue()).getMustSync()) {
						controller.setDepartmentHours(cobDepartmentWorkHours.getValue(),
								TFStartWorkTime.getText(),cbWorkFromHome.isSelected());
					}
					else if(controller.isEmployeePerHour(cobDepartmentWorkHours.getValue(), cobEmployee.getValue())) {
						controller.setPerHourEmployeeWorkTimes(cobDepartmentWorkHours.getValue(), cobEmployee.getValue(),
								TFStartWorkTime.getText(), TFEndWorkTime.getText(),cbWorkFromHome.isSelected());
					}
					else if(cobEmployee.getValue()!=null){
						controller.setEmployeeWorkTimes(cobDepartmentWorkHours.getValue(), cobEmployee.getValue(), TFStartWorkTime.getText(), cbWorkFromHome.isSelected());
					}
				}
			}
		});
		
		
		
		btConfirmUpdateCompanyMonthlySales.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(checkMonthlySalesInput()) {
					controller.UpdateMontlySales(Integer.parseInt(TFMonthlySales.getText()));
					TFMonthlySales.clear();
				}
				
			}

private boolean checkMonthlySalesInput() {
	if(TFMonthlySales.getText().isBlank()) {
		showMsg("Monthly Sales Cant be Blank");
		return false;
	}
	else {
		String monthlySales = TFMonthlySales.getText();
		for(int i =0;i<TFMonthlySales.getText().length();i++) {
			if(monthlySales.charAt(i)< '0'||monthlySales.charAt(i)>'9') {
				showMsg("Monthly Sales Must Be A Positive Number");
				return false;
			}
		}
	}
	return true;
}
		});
		

		
		
		
		
		
		btContinueEmployee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(cobDepartmentEmployee.getValue()==null)
					showMsg("Must Fill All Fields");
				else {
					setVbEmployee(cobDepartmentEmployee.getValue());
				}
				
			}

private void setVbEmployee(String departmentName) {
	cobRolesByDepartment.getItems().clear();
	ArrayList<Role> roles= controller.getRolesInDepartment(departmentName) ;
	for(Role r : roles) {
		cobRolesByDepartment.getItems().add(r.getNameOfRole());
	}
	
	vbAddEmployee.getChildren().clear();
	vbAddEmployee.getChildren().addAll(TEmployeeName,TFEmployeeName,TEmployeeID,TFEmployeeID,TDateOfBirth,dpBDay,TPreferenceOfEmployee,
			cobPreferenceEmployee,TRole,cobRolesByDepartment,TSalaryPreferences,cobSalaryPreferences,btEmployeeGoBack);
}
		});
		
		btUpdateCompanyMonthlySales.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vbUpateMonthlySales.getChildren().clear();;
				 vbUpateMonthlySales.getChildren().addAll(TMonthlySales,TFMonthlySales,btConfirmUpdateCompanyMonthlySales);
				 bpMainScreen.setCenter(vbUpateMonthlySales);
			}
		});
		btAddEmployee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				resetCobDepartment(cobDepartmentEmployee);
				vbAddEmployee.getChildren().clear();
				vbAddEmployee.getChildren().addAll(TDepartment,cobDepartmentEmployee,btContinueEmployee,TWarningOfOverRide);
				bpMainScreen.setCenter(vbAddEmployee);
			}
		});
		
		
		
		btEmployeeGoBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				resetCobDepartment(cobDepartmentEmployee);
				vbAddEmployee.getChildren().clear();
				vbAddEmployee.getChildren().addAll(TDepartment,cobDepartmentEmployee,btContinueEmployee,TWarningOfOverRide);
				bpMainScreen.setCenter(vbAddEmployee);
			}
		});
		
		
		btConfirmAddEmployee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				/*
				 *
				 * CHECK IF DEPARTMENT CAN CHANGE WORK HOURS
				 * CHECK IF DEPARTMENT MUST SYNC 
				 * CHECK IF ROLE CAN CHANGE WORK HOURS
				 * SET VBOX TO PREVIEUS MANU
				 * 
				 */
				if(checkEmployeeInput()) {
				try {
					
					if(cobSalaryPreferences.getValue().equals("Base Salary")) {
						controller.addBaseSalaryEmployee(cobDepartmentEmployee.getValue(),TFEmployeeName.getText(),TFEmployeeID.getText(),dpBDay.getValue(),
								cobPreferenceEmployee.getValue(),cobRolesByDepartment.getValue(),(Integer.parseInt(TFBaseSalary.getText())));
					}
					
					if(cobSalaryPreferences.getValue().equals("Salary Per Hour")) {
						controller.addPerHourEmployee(cobDepartmentEmployee.getValue(),TFEmployeeName.getText(),
								TFEmployeeID.getText(),dpBDay.getValue(),
								cobPreferenceEmployee.getValue(),cobRolesByDepartment.getValue(),
								Integer.parseInt(TFSalaryPerHour.getText()),Integer.parseInt(TFWorkHoursPerMonth.getText()));
					}
					
					if(cobSalaryPreferences.getValue().equals("Base Salary + Percentage of all sales")) {
						controller.addBasePercentEmployee(cobDepartmentEmployee.getValue(),TFEmployeeName.getText(),
								TFEmployeeID.getText(),dpBDay.getValue(),
								cobPreferenceEmployee.getValue(),cobRolesByDepartment.getValue(),
								(Integer.valueOf(TFBaseSalary.getText())),Double.parseDouble(TFPercentFromAllSales.getText()));
					}
					
					clearEmployeeTF();
					showMsg("Employee Was Added");
					
				}
				catch (Exception e) {
					showMsg("Somthing went wrong... Employee was not added\n"+e.getMessage());
					
				}
					
				}
			}


			private void clearEmployeeTF() {
				TFEmployeeName.clear();
				TFEmployeeID.clear();
				TFPercentFromAllSales.clear();
				TFSalaryPerHour.clear();
				TFBaseSalary.clear();
				TFWorkHoursPerMonth.clear();
				
			}
			
			
			private boolean checkEmployeeInput() {
				if(TFEmployeeName.getText().isBlank()) {
					showMsg("Name Cant be Blank");
					return false;
				}
				
				if(TFEmployeeID.getText().length()!=9) {
					showMsg("ID Must be a 9 digits");
					return false;
				}
				String id = TFEmployeeID.getText();
				for(int i =0;i<TFEmployeeID.getText().length();i++) {
					if(id.charAt(i)< '0'||id.charAt(i)>'9') {
						showMsg("ID Must be a 9 digits");
						return false;
					}
				}
				LocalDate birthDay = dpBDay.getValue();
				LocalDate minAgeToWork = LocalDate.now().plusYears(-18);
				if(birthDay ==null||birthDay.compareTo(minAgeToWork)>=0) {
					showMsg("Employee Must Be At List 18 Years Old");
					return false;
				}

				
				if(cobPreferenceEmployee.getValue()==null) {
					showMsg("Please Select Preference");
					return false;
				}
				
				if(vbAddEmployee.getChildren().contains(vbBaseSalary)||vbAddEmployee.getChildren().contains(vbBaseSalaryPercent)) {
					if(TFBaseSalary.getText().isBlank()) {
						showMsg("Please enter Base Salary");
						return false;
					}
					String baseSalary = TFBaseSalary.getText();
					for(int i=0;i<baseSalary.length();i++) {
						if(baseSalary.charAt(i)<'0'||baseSalary.charAt(i)>'9') {
							showMsg("Base Salary needs to be Numbers Only");
							return false;
						}
					}
				}	
				
				if(vbAddEmployee.getChildren().contains(vbPerHour)) {
					if(TFSalaryPerHour.getText().isBlank()) {
						showMsg("Please enter Salary per hour");
						return false;
					}
					String salaryPerHour = TFSalaryPerHour.getText();
					for(int i=0;i<salaryPerHour.length();i++) {
						if(salaryPerHour.charAt(i)<'0'||salaryPerHour.charAt(i)>'9') {
							showMsg("Salary per hour needs to be Numbers Only");
							return false;
						}
					}
					if(TFWorkHoursPerMonth.getText().isBlank()) {
						showMsg("Please enter Work Hours Per Month");
						return false;
					}
					String workHoursPerMonth = TFWorkHoursPerMonth.getText();
					for(int i=0;i<workHoursPerMonth.length();i++) {
						if(workHoursPerMonth.charAt(i)<'0'||workHoursPerMonth.charAt(i)>'9') {
							showMsg("Work Hours Per Month needs to be Numbers Only");
							return false;
						}
					}
				}	
				
				
				
				if(vbAddEmployee.getChildren().contains(vbBaseSalaryPercent)) {
					if(TFPercentFromAllSales.getText().isBlank()) {
						showMsg("Please enter precent of all sales between 0-"+controller.getRemainingPrecentOfAllSales());
						return false;
					}
					String precentOfAllSales = TFPercentFromAllSales.getText();
					double precent;
					try {
						precent = Double.parseDouble(precentOfAllSales);
					}
					catch (Exception e) {
						showMsg("Precent Must be a double");
						return false;
					}
					if(precent<0||precent> controller.getRemainingPrecentOfAllSales()) {
						showMsg("Please enter precent of all sales between 0-"+controller.getRemainingPrecentOfAllSales());
						return false;
					}
				}
				
				
				
				return true;
					
				
			}
		});
		
		
		
		cbCantChangeWorkHoursDepartment.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(cbCantChangeWorkHoursDepartment.isSelected()) {
					cbMustSyncDepartmentWorkHours.setSelected(false);
					cbMustSyncDepartmentWorkHours.setVisible(false);
					cobPreferenceDepartment.setVisible(false);
				}
				else {
					cbMustSyncDepartmentWorkHours.setVisible(true);
					cobPreferenceDepartment.setVisible(true);
				}
					
			}
		});
		
		btInputInformation.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vbSecondryMainButtons.getChildren().clear();
				vbSecondryMainButtons.getChildren().addAll(TInputInformation,btAddDepartment,btAddRole,btAddEmployee,btSetEmployeesWorkHours,btUpdateCompanyMonthlySales,btWindowSetCompanyName);
			}
		});
		
		
		
		
		btShowInformation.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vbSecondryMainButtons.getChildren().clear();
				vbSecondryMainButtons.getChildren().addAll(TShowInformation,btShowAllEmployees,btShowAllDepartments,btShowAllRolesInDepartment,btShowCompanyData);
			}
		});
		
		btChangeWorkHoursSyncRole.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vbSecondryMainButtons.getChildren().clear();
				vbChangeRolePreference.getChildren().clear();
				resetCobDepartment(cobDepartmentChangeRolePref);			
				vbChangeRolePreference.getChildren().addAll(TDepartment,cobDepartmentChangeRolePref,TRole,cobRoleChangePref,cbChangeWHRoleCanChangeWorkHours,btConfirmChangeRolePreference);
				bpMainScreen.setCenter(vbChangeRolePreference);
			
			}
		});
		
		
		btConfirmChangeRolePreference.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(cobDepartmentChangeRolePref.getValue()==null||cobRoleChangePref.getValue()==null) {
					showMsg("Must Fill All Fields");
				}
				else {
					try {	
						controller.updateCanChangeWorkHoursRole(cobDepartmentChangeRolePref.getValue(),cobRoleChangePref.getValue(),!cbChangeWHRoleCanChangeWorkHours.isSelected());
						showMsg("Updated Succesfully");
					} catch (Exception e) {
						showMsg(e.getMessage());
					}
				}
			}
		});
		

		
		cobDepartmentChangeRolePref.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(cobDepartmentChangeRolePref.getValue()!=null) {	
					cobRoleChangePref.getItems().clear();
					cobRoleChangePref.getItems().addAll(controller.getStringRolesArrByDepartment(cobDepartmentChangeRolePref.getValue()));	
				}
				
				
			}
		});
		
		
		
		btChangeWorkHoursPreferenceDepartment.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vbSecondryMainButtons.getChildren().clear();
				vbChangeDepartmentPref.getChildren().clear();
				resetCobDepartment(cobDepartmentChangePref);
				vbChangeDepartmentPref.getChildren().addAll(TDepartment,cobDepartmentChangePref,cbDepartmentChangePrefCanChangeWorkHours,
						cbDepartmentChangePrefMustSync,TPreference,cobPreferenceDepartment,btConfirmChangeWorkHoursPreferenceDepartment);
				bpMainScreen.setCenter(vbChangeDepartmentPref);
			}
		});
		
		
		btConfirmChangeWorkHoursPreferenceDepartment.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(cobDepartmentChangePref.getValue()!=null&&cobPreferenceDepartment.getValue()!=null) {
					controller.updateDepartmentPreference(cobDepartmentChangePref.getValue(),!cbDepartmentChangePrefCanChangeWorkHours.isSelected(),
							cbDepartmentChangePrefMustSync.isSelected(),cobPreferenceDepartment.getValue());
				}
				else {
					showMsg("Must Fill All Fields");
				}
				
			}
		});
		
		btShowProfit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vbSecondryMainButtons.getChildren().clear();
				vbSecondryMainButtons.getChildren().addAll(TShowProfit,btShowEmployeesProfit,btShowDepartmentProfit,btShowCompanyProfit);
			}
		});
		
		
		
		
		cobSalaryPreferences.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(cobSalaryPreferences.getValue()!=null) {
					if(cobSalaryPreferences.getValue().equals("Base Salary")) 
						setVbBaseSalary();
					else if(cobSalaryPreferences.getValue().equals("Base Salary + Percentage of all sales"))
						setVbBaseSalaryPercent();	
					else if(cobSalaryPreferences.getValue().equals("Salary Per Hour"))
						setVbPerHour();
				}
							
			}
		}); 	
		 	btAutoGenerete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				controller.autoGenData();
				bpMainScreen.setCenter(TMainScreen);
				bpMainScreen.setLeft(vbMainButtons);
				bpMainScreen.setBottom(hbExitButton);
			}
		}); 
		 
		 
		btDontAutoGenerete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vbLoadingProccess.getChildren().clear();
				vbLoadingProccess.getChildren().add(hbLoadFromFile);
			}
		});
		
		
		
		btLoadFromFile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(controller.loadFromFile()) {
					bpMainScreen.setCenter(TMainScreen);
					bpMainScreen.setLeft(vbMainButtons);
					bpMainScreen.setBottom(hbExitButton);
				}
				
			}
		});
		
		btDontLoadFromFile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				bpMainScreen.setCenter(vbEnterCompanyName);
			}
		});
		
		btWindowSetCompanyName.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				bpMainScreen.setCenter(vbEnterCompanyName);
			}
		});
		
		
		btSetCompanyName.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(TFCompanyName.getText().isBlank()) {
					showMsg("Company Name Cant Be Blank");
				}
				else {
					controller.setCompanyName(TFCompanyName.getText());
					
					bpMainScreen.setCenter(TMainScreen);
					bpMainScreen.setBottom(hbExitButton);
					bpMainScreen.setLeft(vbMainButtons);
				}
				
			
			}
		});

		btExit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				controller.saveToFile();
				System.exit(0);
			}
		}); 
		 
		btAddDepartment.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vbAddDepartment.getChildren().clear();
				vbAddDepartment.getChildren().addAll(TDepartmentName,TFDepartmentName,cbCantChangeWorkHoursDepartment,
						cbMustSyncDepartmentWorkHours,cobPreferenceDepartment,btConfirmAddDepartment);
				bpMainScreen.setCenter(vbAddDepartment);
			}
		}); 
		
		btConfirmAddDepartment.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(TFDepartmentName.getText().isBlank())
					showMsg("Must Fill all Fields");
				else {
					if(cbCantChangeWorkHoursDepartment.isSelected()) {
						controller.addDepartment(TFDepartmentName.getText(),!cbCantChangeWorkHoursDepartment.isSelected(),true,preferences.get(2));
					}	
					else if(cobPreferenceDepartment.getValue()==null)
						showMsg("Must Fill all Fields");
					else {
						controller.addDepartment(TFDepartmentName.getText(),!cbCantChangeWorkHoursDepartment.isSelected(),cbMustSyncDepartmentWorkHours.isSelected(),preferences.get(2));
					}
						
				}
					
			}
		}); 
		
		btAddRole.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				resetCobDepartment(cobDepartmentRole);
				bpMainScreen.setCenter(vbAddRole);
			}


		}); 
		

		
		btPicOfDuck.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				bpMainScreen.setCenter(hbDuckPhoto);
				bpMainScreen.setLeft(vbEmpty);
				bpMainScreen.setBottom(btBackToMainScreen);
			}
		}); 
		
		btBackToMainScreen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				bpMainScreen.setLeft(vbMainButtons);
				bpMainScreen.setCenter(TMainScreen);
				bpMainScreen.setBottom(hbExitButton);
			}
		}); 
		
		
		
		btConfirmAddRole.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(cobDepartmentRole.getValue()==null||TFNameOfRole.getText().isBlank())
					showMsg("Must Fill All Fields");
				else {
					controller.addRoleToDepartment(cobDepartmentRole.getValue(),TFNameOfRole.getText(),!cbCantChangeWorkHoursRole.isSelected());
				}
			}
		});
		
		
		btShowAllEmployees.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vbShowAllEmployees.getChildren().clear();
				vbShowAllEmployees.getChildren().add(new Text(controller.getAllEmployeesString()));
				bpMainScreen.setCenter(spShowEmployees);
			}


		}); 
		btShowAllDepartments.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vbShowAllDepartments.getChildren().clear();
				vbShowAllDepartments.getChildren().add(new Text(controller.getAllDepartmentsString()));
				bpMainScreen.setCenter(spShowAllDepartments);
			}


		}); 
		
		btShowAllRolesInDepartment.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vbShowAllRolesInDepartment.getChildren().clear();
				resetCobDepartment(cobDepartmentsShowRoles);
				TShowRolesInDepartment.setText("");
				vbShowAllRolesInDepartment.getChildren().addAll(TChooseDepartmentShowRoles,cobDepartmentsShowRoles,TShowRolesInDepartment);
				bpMainScreen.setCenter(spShowAllRolesInDepartment);
			}
			
		}); 
		
		btShowCompanyData.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				vbShowCompanyData.getChildren().clear();
				vbShowCompanyData.getChildren().add(new Text(controller.getCompanyDataString()));
				bpMainScreen.setCenter(spShowAllCompanyData);
			}
			
		}); 
		
//************************************************************************************************************************************************************* 
		 
		 

		 
		 
		 Scene scene = new Scene(bpMainScreen, 800, 600);
			primaryStage.setScene(scene);
			primaryStage.show();
			
	}
	
	

	
	
	
	public static void showMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
	
	public void setVbBaseSalary() {
		vbAddEmployee.getChildren().clear();
		vbBaseSalary.getChildren().clear();
		vbBaseSalary.getChildren().addAll(TBaseSalary,TFBaseSalary,btConfirmAddEmployee);
		vbAddEmployee.getChildren().addAll(TEmployeeName,TFEmployeeName,TEmployeeID,TFEmployeeID,TDateOfBirth,dpBDay,TPreferenceOfEmployee,
				cobPreferenceEmployee,TRole,cobRolesByDepartment,TSalaryPreferences,cobSalaryPreferences,vbBaseSalary,btEmployeeGoBack);
	}
	
	public void setVbPerHour() {
		vbAddEmployee.getChildren().clear();
		vbPerHour.getChildren().clear();
		vbPerHour.getChildren().addAll(TSalaryPerHour,TFSalaryPerHour,TWorkHoursPerMonth,TFWorkHoursPerMonth,btConfirmAddEmployee);
		vbAddEmployee.getChildren().addAll(TEmployeeName,TFEmployeeName,TEmployeeID,TFEmployeeID,TDateOfBirth,dpBDay,TPreferenceOfEmployee,
				cobPreferenceEmployee,TRole,cobRolesByDepartment,TSalaryPreferences,cobSalaryPreferences,vbPerHour,btEmployeeGoBack);
	}
	public void setVbBaseSalaryPercent() {
		vbAddEmployee.getChildren().clear();
		vbBaseSalaryPercent.getChildren().clear();
		vbBaseSalaryPercent.getChildren().addAll(TBaseSalary,TFBaseSalary,TPercentFromAllSales,TFPercentFromAllSales,btConfirmAddEmployee);
		vbAddEmployee.getChildren().addAll(TEmployeeName,TFEmployeeName,TEmployeeID,TFEmployeeID,TDateOfBirth,dpBDay,TPreferenceOfEmployee,
				cobPreferenceEmployee,TRole,cobRolesByDepartment,TSalaryPreferences,cobSalaryPreferences,vbBaseSalaryPercent,btEmployeeGoBack);
	    
	}
	
	
	private void resetCobDepartment(ComboBox<String> cobDepartment) {
		cobDepartment.getItems().clear();
		cobDepartment.getItems().addAll(controller.getDepartmentsNames());
		
	}
	
	private void resetCobEmployee(String departmentName,ComboBox<String> cobEmployee) {
		cobEmployee.getItems().clear();
		cobEmployee.getItems().addAll(controller.getEmployeesInDepartment(departmentName));
	}

}
