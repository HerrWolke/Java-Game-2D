package de.marcus.javagame.world;

public class Eingang {
   public int mapType;
    int map;
    public Eingang(float x, float y,int map){
           this.map = map;
           if(x >154 && x<158 && y > 135 && y < 140 && map ==0){
                  mapType = 5;
           }else if(x >50 && x<56 && y > 154 && y < 163 && map ==0){
                  mapType = 1;
           }else if(x >60 && x<70 && y > 154 && y < 163 && map ==0){
                  mapType = 2;
           }else if(x >138 && x<142 && y > 109 && y < 113 && map ==0){
                  mapType = 3;
           }else if(x >205 && x<208 && y > 148 && y < 153 && map ==0){
                  mapType = 3;
           }else if(x >135 && x<140 && y > 97 && y < 101 && map ==0){
                  mapType = 5;
           }else if(x >47 && x<52 && y > 105 && y < 109 && map ==0){
                  mapType = 6;
           }else if(x >221 && x<224 && y > 121 && y < 125 && map ==0){
                  mapType = 1;
           }else if(x >197 && x<201 && y > 109 && y < 115 && map ==0){
                  mapType = 5; //tp zu diesem
           }else if(x >187 && x<190 && y > 110 && y < 114 && map ==0){
                  mapType = 5; //tp von hier nach
           }else if(x >161 && x<165 && y > 115 && y < 121 && map ==0){
                  mapType = 2;
           }else if(x >177 && x<180 && y > 99 && y < 105 && map ==0){
                  mapType = 1;
           }else if(x >153 && x<157 && y > 111 && y < 116 && map ==0){
                  mapType = 2;
           }else if(x >182 && x<186 && y > 190 && y < 195 && map ==0){
                  mapType = 1;
           }else if(x >150 && x<155 && y > 229 && y < 234 && map ==0){
                  mapType = 3;
           }else if(x >140 && x<145 && y > 229 && y < 233 && map ==0){
                  mapType = 2;
           }else if(x >200 && x<204 && y > 262 && y < 267 && map ==0){
                  mapType = 3;
           }else if(x >192 && x<196 && y > 257 && y < 262 && map ==0){
                  mapType = 2;
           }else if(x >60 && x<64 && y > 135 && y < 140 && map ==0){
                  mapType = 6;
           }else if(x > 13 && x <20 && y > 70 && y < 76 && map == 1){
                  mapType =2;
           }
    }
}
