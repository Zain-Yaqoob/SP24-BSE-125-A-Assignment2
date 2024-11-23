package com.example.demo2;


import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class App2 extends Application {

    private String submittedName;
    private String submittedFatherName;
    private String submittedPhoneNumber;
    private String submittedCity;
    private String submittedImagePath;

    private boolean formSubmitted = false;

    // ArrayList to store user information
    private ArrayList<String> userInfo = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Stylish JavaFX Form");

        // Form GridPane
        GridPane formGrid = createFormGrid(primaryStage);

        // Scene and Stage Setup
        Scene formScene = new Scene(formGrid, 800, 600);

        // Apply custom styling directly in the code
        primaryStage.setScene(formScene);
        primaryStage.show();
    }

    private GridPane createFormGrid(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: linear-gradient(to bottom, #1e2a47, #4a4e69);");  // Gradient background

        // Title Box for "User Information Form"
        VBox headerBox = new VBox(10);
        headerBox.setStyle("-fx-background-color: #333333; -fx-padding: 15px; -fx-border-radius: 10px;");
        headerBox.setAlignment(Pos.CENTER);
        Text scenetitle = new Text("User Information Form");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
        scenetitle.setFill(Color.WHITE);
        headerBox.getChildren().add(scenetitle);
        grid.add(headerBox, 0, 0, 2, 1);

        // Name field
        grid.add(createStyledLabel("Name:"), 0, 1);
        TextField nameField = new TextField();
        nameField.setStyle("-fx-font-size: 14px; -fx-background-color: #fff; -fx-border-color: #ccc; -fx-padding: 5px;");
        grid.add(nameField, 1, 1);

        // Father Name field
        grid.add(createStyledLabel("Father Name:"), 0, 2);
        TextField fatherNameField = new TextField();
        fatherNameField.setStyle("-fx-font-size: 14px; -fx-background-color: #fff; -fx-border-color: #ccc; -fx-padding: 5px;");
        grid.add(fatherNameField, 1, 2);

        // Phone Number field
        grid.add(createStyledLabel("Phone Number:"), 0, 3);
        TextField phoneNumberField = new TextField();
        phoneNumberField.setStyle("-fx-font-size: 14px; -fx-background-color: #fff; -fx-border-color: #ccc; -fx-padding: 5px;");
        grid.add(phoneNumberField, 1, 3);

        // City field
        grid.add(createStyledLabel("City Address:"), 0, 4);
        TextField cityField = new TextField();
        cityField.setStyle("-fx-font-size: 14px; -fx-background-color: #fff; -fx-border-color: #ccc; -fx-padding: 5px;");
        grid.add(cityField, 1, 4);

        // Upload Image button
        grid.add(createStyledLabel("Upload Image:"), 0, 5);
        Button uploadImageButton = new Button("Browse");
        uploadImageButton.setStyle("-fx-font-size: 14px; -fx-background-color: #007BFF; -fx-text-fill: white; -fx-padding: 10px; -fx-border-radius: 5px;");
        grid.add(uploadImageButton, 1, 5);

        // To track uploaded image path
        final Text imageStatus = new Text();
        imageStatus.setFill(Color.WHITE);
        grid.add(imageStatus, 1, 6);

        // Submit button
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px; -fx-border-radius: 5px;");
        HBox hbBtnSubmit = new HBox(10);
        hbBtnSubmit.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnSubmit.getChildren().add(submitButton);
        grid.add(hbBtnSubmit, 1, 7);

        // View Info button
        Button viewInfoButton = new Button("View Info");
        viewInfoButton.setDisable(true);
        viewInfoButton.setStyle("-fx-font-size: 14px; -fx-background-color: #17A2B8; -fx-text-fill: white; -fx-padding: 10px; -fx-border-radius: 5px;");
        HBox hbBtnView = new HBox(10);
        hbBtnView.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnView.getChildren().add(viewInfoButton);
        grid.add(hbBtnView, 1, 8);

        // Status text
        final Text actionTarget = new Text();
        actionTarget.setFill(Color.WHITE);
        grid.add(actionTarget, 1, 9);

        // File chooser for image upload
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        uploadImageButton.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                submittedImagePath = file.getAbsolutePath();
                imageStatus.setFill(Color.GREEN);
                imageStatus.setText("Image selected successfully!");
            } else {
                imageStatus.setFill(Color.FIREBRICK);
                imageStatus.setText("No image selected.");
            }
        });

        // Handle Submit button click
        submitButton.setOnAction((ActionEvent e) -> {
            submittedName = nameField.getText();
            submittedFatherName = fatherNameField.getText();
            submittedPhoneNumber = phoneNumberField.getText();
            submittedCity = cityField.getText();

            if (submittedName.isEmpty() || submittedFatherName.isEmpty() || submittedPhoneNumber.isEmpty() || submittedCity.isEmpty() || submittedImagePath == null) {
                actionTarget.setFill(Color.FIREBRICK);
                actionTarget.setText("All fields are required!");
                return;
            }

            actionTarget.setFill(Color.GREEN);
            actionTarget.setText("Form submitted successfully!");
            formSubmitted = true;
            viewInfoButton.setDisable(false);

            // Add user info to ArrayList
            userInfo.clear(); // Clear any existing info
            userInfo.add("Name: " + submittedName);
            userInfo.add("Father Name: " + submittedFatherName);
            userInfo.add("Phone Number: " + submittedPhoneNumber);
            userInfo.add("City: " + submittedCity);
            userInfo.add("Image Path: " + submittedImagePath);

            actionTarget.setFill(Color.GREEN);
            actionTarget.setText("Form submitted successfully!");
            formSubmitted = true;
            viewInfoButton.setDisable(false);
        });

        // Handle View Info button click
        viewInfoButton.setOnAction((ActionEvent e) -> {
            if (formSubmitted) {
                // Switch to the information view
                GridPane infoGrid = createInfoGrid(primaryStage);
                Scene infoScene = new Scene(infoGrid, 800, 600);
                primaryStage.setScene(infoScene);

                // Print user information to console
                System.out.println("User Information:");
                for (String info : userInfo) {
                    System.out.println(info);  // This will display in the console
                }
            }
        });

        return grid;
    }

    // Helper method to create bold, white labels
    private Label createStyledLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
        return label;
    }

    private GridPane createInfoGrid(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: linear-gradient(to bottom, #6a4c93, #b497bd);");  // Gradient background for second screen

        // Title Box for "Submitted Information"
        VBox headerBox = new VBox(10);
        headerBox.setStyle("-fx-background-color: #333333; -fx-padding: 15px; -fx-border-radius: 10px;");
        headerBox.setAlignment(Pos.CENTER);
        Text scenetitle = new Text("Submitted Information");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
        scenetitle.setFill(Color.WHITE);
        headerBox.getChildren().add(scenetitle);
        grid.add(headerBox, 0, 0, 2, 1);

        grid.add(createStyledLabel("Name:"), 0, 1);
        Label nameLabel = new Label(submittedName);
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
        grid.add(nameLabel, 1, 1);

        grid.add(createStyledLabel("Father Name:"), 0, 2);
        Label fatherNameLabel = new Label(submittedFatherName);
        fatherNameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
        grid.add(fatherNameLabel, 1, 2);

        grid.add(createStyledLabel("Phone Number:"), 0, 3);
        Label phoneNumberLabel = new Label(submittedPhoneNumber);
        phoneNumberLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
        grid.add(phoneNumberLabel, 1, 3);

        grid.add(createStyledLabel("City Address:"), 0, 4);
        Label cityLabel = new Label(submittedCity);
        cityLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
        grid.add(cityLabel, 1, 4);

        // Display the selected image
        grid.add(createStyledLabel("Uploaded Image:"), 0, 5);
        if (submittedImagePath != null) {
            Image image = new Image("file:///" + submittedImagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            grid.add(imageView, 1, 5);
        }

        // Back Button to return to the form
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 14px; -fx-background-color: #FFC107; -fx-text-fill: white; -fx-padding: 10px; -fx-border-radius: 5px;");
        backButton.setOnAction(event -> {
            GridPane formGrid = createFormGrid(primaryStage);
            Scene formScene = new Scene(formGrid, 800, 600);
            primaryStage.setScene(formScene);
        });

        HBox hbBtnBack = new HBox(10);
        hbBtnBack.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnBack.getChildren().add(backButton);
        grid.add(hbBtnBack, 1, 7);

        return grid;
    }
}
