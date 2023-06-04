# Recipe-Book-Application
Author: Thiem Quyen

## Project Description
Users can create, read, update and delete recipes in this app. In addition, it can generate a shopping list from a recipe.  

The project includes, but is not limited to:  
- SQLite DB  
- Fragments  
- Recycler View  
- OptionsMenu  
- Error Handling in Dialog  

## Architecture and DB UML
![image](https://github.com/dtquyen1199/Recipe-Book-Application/assets/88473863/4c918f49-7ec5-46f2-a5a2-eb27061cb031)

![image](https://github.com/dtquyen1199/Recipe-Book-Application/assets/88473863/d46d8241-8275-4896-9aba-1bc2a36de836)

## Installation
Clone project and run on Android Studio's emulator or on connected device.



## App In Action

![image](https://github.com/dtquyen1199/Recipe-Book-Application/assets/88473863/023396c7-b8fc-41be-a5dc-45ef39c98627)  

List of Recipes/Home  

![image](https://github.com/dtquyen1199/Recipe-Book-Application/assets/88473863/0a4468ac-195b-4b0e-89f7-64fa4522fa63)  

Creating/Editing a recipe  

![image](https://github.com/dtquyen1199/Recipe-Book-Application/assets/88473863/b9cfa3bc-abda-41ea-80e9-d163ae90cc16)  

OptionsMenu to either delete a recipe or create a shopping list   

![image](https://github.com/dtquyen1199/Recipe-Book-Application/assets/88473863/dc4e3a13-c028-42aa-a27d-037e98bb7769)  

Dialog  

![image](https://github.com/dtquyen1199/Recipe-Book-Application/assets/88473863/bbdc152b-a6b8-4b6e-88d0-c26fbef4d5c6)  

Shopping List  

## Open Issues and Recommendations
1/ Room database can be used to avoid boilerplate code.  
2/ Live Data and ViewModel can be used to listen to changes to the database.  
3/ The app can expand to have PantryFragment. The user should be able to save their existing food in there. Nominated relational database if PantryFragment is implemented:  
![image](https://github.com/dtquyen1199/Recipe-Book-Application/assets/88473863/1614d32d-fa24-48a8-a4d6-2f579d6a198d)


