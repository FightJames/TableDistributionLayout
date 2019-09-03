TableDistributionLayout
===

## Demo 
![image](https://media.giphy.com/media/IeXBR6ICuaWrmtmW91/giphy.gif)

## How to use

Add this in gradle
In project gradle
```
buildscript {
...
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
}

```
In app gradle
```
    implementation 'com.github.FightJames:TableDistributionLayout:v1.0.4'

```


```
            <com.numbertech.tabledistributionlayoutlib.TableDistributionLayout
                android:id="@+id/tableDistributionLayout"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                app:columnCount="3"
                app:itemSpaceHeight="5dp"
                app:itemSpaceWidth="5dp"
                app:layout_constrainedHeight="true"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent">

```
### Attribute
itemSpaceHeight : Item and Item vertical space. You can set  vertical distent with this attribute.
The red line in picture.
![](https://i.imgur.com/xvG6vlE.png)
 

itemSpaceWidth : Item and Item horizontal space. You can set  horizontal distent with this attribute.
The red line in picture.
![](https://i.imgur.com/KoQt4oA.png)

columnCount : Item count each row. You can set how many item show in each row.
This example columnCount is 3. 
![](https://i.imgur.com/7x73cn1.png)

