# Java-Game-2D
Rising Mage - An adventure game

Folder structure:

- **entities: all files part of the entity system**
  - base: all base classes which are not actually implemented but are only there to be extended by another class
  - data: classes that store data for specific entites (p.e. StatusEffect)
  - impl: the implenting classes of "base" folder. Currently only enemies (Shadow)
  
- **framework: biggest package, mostly things concerning handling data and such (But there are some ui files in: dialog, shop and mathgame)**
  - Won't list and explain them. Are pretty self explanatory
  
- **player: All files concerning the player**
  - Won't list and explain them. Are pretty self explanatory
  
- **ui: All files, which show something to the player on the screen (!= world)**
  - Screens: different game states
  - UI: general UI Files
  
- **world: the world the player is walking around in**
