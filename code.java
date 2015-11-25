// Project 3
// Garrett Stoddard
// Objects time
// I'm using the specifications from https://github.com/bam59cst112day/project-3 with the exception below
// *IMPORTANT* instead of stopping the balls when a rat hit them, i increased the speed, because then it worked better
// with the score. if the balls stopped it would make the score a lot smaller, and in the first read.me you said to increase
// the speed https://github.com/Link205293/project-3/blob/master/README.md
// globls

float h = 800;
float w = 1250;
float tableY = h/2+50, tableX = w/2, tableW = w-100, tableH = h-200;
float tableLeft , tableRight;
float tableTop, tableBot;
float friction = .986;
float checkX[] = new float[16];
float checkY[] = new float[16];
float ballX[] = new float[16];
float ballY[] = new float[16];
float ballDX[] = new float[16];
float ballDY[] = new float[16];
Ball [] game = new Ball[16];
Button [] pie = new Button[4];
Rat bob;
Bird mike;
Cloud [] sky = new Cloud[8];
boolean overlap;
int wall = 1;
int collisions = 0;
color b = #067920;
color t = #054307;



void setup()
{
  size( 1250, 800 );
  tableLeft = tableX - tableW/2;
  tableRight = tableX + tableW/2; 
  tableTop = tableY - tableH/2; 
  tableBot = tableY + tableH/2;
  ballX[0] = tableX;
  ballY[0] = tableY;
  ballDX[0] = 0;
  ballDY[0] = 0;
  for(int i = 1; i < 8; i++)
    {
      sky[i] = new Cloud((((i)*width)/8), 40);
    }
  for (int i = 0; i < game.length; i++)
    {
      game[i] = new Ball();
    }
  pie[0] = new Button((((1)*w)/5), 75, 75, 40, #000000);
  pie[1] = new Button((((2)*w)/5), 75, 75, 40, #000000);
  pie[2] = new Button((((3)*w)/5), 75, 75, 40, #000000);
  pie[3] = new Button((((4)*w)/5), 75, 75, 40, #000000);
  pie[0].name = "reset";
  pie[1].name = "wall";
  pie[2].name = "bird";
  pie[3].name = "rat";
  bob = new Rat( -50, random(50, h - 50));
  mike = new Bird( 0, 125);
  reset();
}
// frames
void draw()
{
  scene();
  table();
  buttons();
  balls();
  wall();
  rat();
  clouds();
  grass();
  bird();
}

void scene()
{
  background(250,250,200);
  fill(0);
  text("score :" , 1070, 50);
  text(collisions, 1100, 50);
  
}

void grass()
{
  int i = 1;
  while(i < 10)
  {
    strokeWeight(5);
    stroke(#22CB17);
    line(((width*i)/10), height - 20, ((width*i)/10) - 10, height - 40);
    line(((width*i)/10), height - 20, ((width*i)/10) + 10, height - 40);
    i++;
    strokeWeight(1);
    stroke(0);
  }
}

void reset()
{
  for (int i = 0; i < game.length; i++)
    {
      if (i == 0)
        {
          game[0].x = tableX/2;
          game[0].y = tableY;
          game[0].dx = 0;
          game[0].dy = 0;
          game[0].c = #FFFFFF;
        }
       else
       {
         game[i].x = random( tableX + 25, tableRight - 15);
         game[i].y = random( tableTop + 15, tableBot - 15);
         game[i].dy = random(-5,5);
         game[i].dx = random(-5,5);
         
       }
       game[1].c = #FEFF03;
       game[2].c = #0311FF;
       game[3].c = #FF0303;
       game[4].c = #A300EA;
       game[5].c = #FF7003;
       game[1].name = "1";
       game[2].name = "2";
       game[3].name = "3";
       game[4].name = "4";
       game[5].name = "5";
     
    }
    
    collisions = 0;
    wall = 1;
}

/*void collisions()
{
  for( int j = 0; j <game.length; j++)
  {
    for(int i = 0; i < game.length; i++)
    {
      if (i == j)
      {
      }
      else
      {
        if( dist( game[i].x, game[i].y, game[j].x, game[j].y) < 30)
        {
          game[i].dy*= -1;
          game[i].dx*= -1;
          game[j].dy*= -1;
          game[j].dx*= -1;
        }
    
        }
      }
  }
}
*/
void collision( Ball q, Ball w ) 
{
  if ( q.hit( w.x,w.y ) ) 
  {
    float swap;
    swap=q.dx;  
    q.dx=w.dx;  
    w.dx=swap;      // Swap the velocities.
    swap=q.dy;  
    q.dy=w.dy;  
    w.dy=swap;
    collisions++;
  }
}

void balls()
{
  
  
  
  // collisions
  //collisions();
  
  collision(game[0], game[1]);
  collision(game[0], game[2]);
  collision(game[0], game[3]);
  collision(game[0], game[4]);
  collision(game[0], game[5]);
  
  collision(game[1], game[2]);
  collision(game[1], game[3]);
  collision(game[1], game[4]);
  collision(game[1], game[5]);
  
  collision(game[2], game[3]);
  collision(game[2], game[4]);
  collision(game[2], game[5]);
  
  collision(game[3], game[4]);
  collision(game[3], game[5]);
  
  collision(game[4], game[5]);
  
  game[0].move();
  game[1].move();
  game[2].move();
  game[3].move();
  game[4].move();
  game[5].move();
  
  game[0].show();
  game[1].show();
  game[2].show();
  game[3].show();
  game[4].show();
  game[5].show();
  textAlign(CENTER,CENTER);
  text(game[1].name, game[1].x, game[1].y);
  text(game[2].name, game[2].x, game[2].y);
  text(game[3].name, game[3].x, game[3].y);
  text(game[4].name, game[4].x, game[4].y);
  text(game[5].name, game[5].x, game[5].y);
}

void buttons()
{
  pie[0].show();
  pie[1].show();
  pie[2].show();
  pie[3].show();
  
}

void rat()
{
  bob.show();
  bob.move();
  bob.attack(game[0]);
  bob.attack(game[1]);
  bob.attack(game[2]);
  bob.attack(game[3]);
  bob.attack(game[4]);
  bob.attack(game[5]);
}

void clouds()
{
  for(int i = 1; i < 8; i++)
  {
    sky[i].show();
    sky[i].move();
  }
}

void bird()
{
  mike.show();
  mike.move();
  mike.bombsAway();
  
}

//Wall method
void wall()
{
  rectMode(CENTER);
  if (wall == 1)
  {
    rectMode(CENTER);
    rect(tableX, tableY, 25, tableH);
  }
  else
  {
    
  }
}

void table()
{
  rectMode(CENTER);
  fill(#392601);
  rect(tableX, tableY, w-50, h-150);
  fill(t);
  rect(tableX, tableY, tableW, tableH);
}

class Button
{
  //Properties
  float x, y, h, w;
  color c;
  String name;
  boolean action = false;
  
  // constructor
  Button(float tempX, float tempY, float tempW, float tempH, color tempColor)
  {
    x = tempX;
    y = tempY;
    w = tempW;
    h = tempH;
    c = tempColor;
  }
  
  // actions
  void show()
  {
    rectMode(CENTER);
    fill(c);
    rect(x, y, w, h);
    rectMode(CORNER);
    fill(255);
    textAlign(CENTER);
    text(name, x, y);
  }
  
    boolean click(float x2, float y2, float w, float h ) 
  {
    boolean result;
  
    if ( abs(x-x2) < w && abs(y-y2)<h ) {
      result=  true;
    } else {
      result=false;
    }
  
    return result;
  }
  
}
 
class Ball 
{
  //Properties
  float x, y, dx, dy;
  color c;
  String name;
  boolean striped;
  
  // constructor
  /*Ball( float tempX, float tempY, float tempDX, float tempDY, color tempC)
    {
      x = tempX;
      y = tempY;
      dx = tempDX;
      dy = tempDY;
      c = tempC;
    }
    */
  //methods
  void show()
  {
    fill(c);
    ellipse(x, y, 30, 30);
    fill(0);
    
  }
  
  void move()
  {
    if( wall == 1)
    {
      if( x > tableX - (25) && x < tableX + (25))
      {
        dx = - dx;
      }
    }
      if (x > tableRight - 15)
      {
        dx = - dx;
        x = tableRight - 15;
      }
      else if ( x < tableLeft + 15)
      {
        dx = - dx;
        x = tableLeft + 15;
      }
      if (y > tableBot - 15)
      {
        dy = - dy;
        y = tableBot - 15;
      }
      else if ( y < tableTop + 15 )
      {
        dy = - dy;
        y =  tableTop + 15;
      }
     
      x = x + dx;
      y = y + dy;
      dx = dx * friction;
      dy = dy * friction;
    
}
  
    boolean hit( float x, float y)
  {
    {
      if( dist ( x, y, this.x, this.y) < 30) 
      {
        return true;
      }
      else return false;
    }
  }
}

class Rat
{
  // properties
  float ratX, ratY;
  float ratDX = random(.5, 6), ratDY= random(-6, 6);
  // determines if the rat exists
  float m;
  // controls animation
  float i;
  // constructor
  Rat(float tempX, float tempY)
  {
    ratX = tempX;
    ratY = tempY;
  }
  
  //methods
  void show()
  {
      if( m == 1)
    {
      fill(#A09F9E);
      ellipse(ratX, ratY, 40,30);
      if( ratX > w)
      {
        ratX = -50;
        ratY = random(50, h - 50);
      }
      fill(0);
      ellipse( ratX + 20, ratY, 6, 6);
      ellipse( ratX + 12, ratY - 8, 5, 5);
      stroke(#A09F9E);
      strokeWeight(5);
      if(i%20 >= 11)
      {
        line(ratX - 10,ratY , ratX - 30, ratY + 20);
        line(ratX + 10,ratY , ratX + 30, ratY + 20);
        i++;
      }
      if(i%20 <= 10)
      {
        line(ratX - 10, ratY, ratX-20, ratY + 20);
        line(ratX + 10, ratY, ratX+ 20, ratY + 20);
        i++;
      }
      line( ratX - 20, ratY, ratX - 40, ratY - 10);
      strokeWeight(1);
      stroke(0);
    }
  }
  
  void move()
  {
    if ( m==1)
      {
        ratDX = random(.5, 5);
        ratDY = random(-5, 5);
        ratX += ratDX;
        ratY += ratDY;
      }
  }
  
  void attack(Ball a)
  {
    
    if( dist( a.x, a.y, ratX, ratY) < 20)
      {
        a.dx = ratDX*3;
        a.dy = ratDY*3;
        collisions -= 10;
      }
  }
  
}

// bird class
class Bird
{
  float x, y, bombX, bombY;
  float DX = 3, bombDY = 3;
  float m = 0;
  // properties
  float i;
  
  Bird( float tempX, float tempY)
  {
    x = tempX;
    y = tempY;
    
    bombY = y;
  }
  
  void show()
  {
    if (m >= 1)
    {
      fill(#ED2000);
      triangle(x, y - 10, x, y + 10, x +30, y);
      strokeWeight(5);
      if(i%20 >= 11)
      {
        
        line(x+5, y , x+5, y - 20);
        i++;
      }
      if(i%20 <= 10)
      {
        line(x+5, y, x+5, y + 20);
        i++;
      }
      
    }
    strokeWeight(1);
  }
  
  void move()
  {
    if( x > w)
      {
        x = -50;
      }
    x += DX;
    bombX = x;
    bombDY = bombDY * 1.005;
  }
  
  void bombsAway()
  {
    if( m > 1)
    {
      fill(255);
      ellipse(bombX, bombY, 10, 10);
      bombX += DX;
      bombY += bombDY;
      if( bombY > h)
      {
        bombDY = DX;
        bombY = y;
        m--;
      }
    }
  }
}

//clouds in the sky
class Cloud
{
  float x, y;
  float DX;
  
  Cloud( float tempX, int tempY)
  {
    x = tempX;
    y = tempY;
  }
  
  void show()
  {
     fill(255);
     ellipse( x, y, 50, 40);
  }
  
  void move()
  {
    x+=2;
    if(x > 1250)
    {
      x = 0;
    }
  }
}


// handelers
void keyPressed()
{
  if( key == 'r')
  {
    reset();
  }
}

void mousePressed()
{
  
  
  if(pie[0].click(mouseX, mouseY, pie[0].w, pie[0].h) == true)
    {
      reset();
    }
  if(pie[1].click(mouseX, mouseY, pie[1].w, pie[1].h) == true)
    {
      if(wall ==1)
      {
        wall = 0;
      }
      else
      {
        wall = 1;
      }
    }
  if(pie[2].click(mouseX, mouseY, pie[2].w, pie[2].h) == true)
  {
    mike.m++;
  }
  if(pie[3].click(mouseX, mouseY, pie[3].w, pie[3].h) == true)
    {
      if( bob.m == 0)
        {
          bob.m++;
        }
      else
        {
          bob.m--;
        }
    }
  if(dist(mouseX, mouseY, game[1].x, game[1].y) < 15)
    {
       game[1].x = random( tableX + 15, tableRight - 15);
       game[1].y = random( tableTop + 15, tableBot - 15);
       game[1].dy = random(-5,5);
       game[1].dx = random(-5,5); 
       collisions = collisions - 5;
    }
  if(dist(mouseX, mouseY, game[2].x, game[2].y) < 15)
    {
       game[2].x = random( tableX + 15, tableRight - 15);
       game[2].y = random( tableTop + 15, tableBot - 15);
       game[2].dy = random(-5,5);
       game[2].dx = random(-5,5); 
       collisions = collisions - 5;
    }
  if(dist(mouseX, mouseY, game[3].x, game[3].y) < 15)
    {
       game[3].x = random( tableX + 15, tableRight - 15);
       game[3].y = random( tableTop + 15, tableBot - 15);
       game[3].dy = random(-5,5);
       game[3].dx = random(-5,5); 
       collisions = collisions - 5;
    }
  if(dist(mouseX, mouseY, game[4].x, game[4].y) < 15)
    {
       game[4].x = random( tableX + 15, tableRight - 15);
       game[4].y = random( tableTop + 15, tableBot - 15);
       game[4].dy = random(-5,5);
       game[4].dx = random(-5,5); 
       collisions = collisions - 5;
    }
  if(dist(mouseX, mouseY, game[5].x, game[5].y) < 15)
    {
       game[5].x = random( tableX + 15, tableRight - 15);
       game[5].y = random( tableTop + 15, tableBot - 15);
       game[5].dy = random(-5,5);
       game[5].dx = random(-5,5); 
       collisions = collisions - 5;
    }
   if(dist(mouseX, mouseY, bob.ratX, bob.ratY) < 20)
   {
     if( bob.m == 1)
       {
         bob.m --;
         collisions += 50;
       }
   }
   else if( (mouseX > tableX - tableW/2) && (mouseX < tableX + tableW/2) 
    && (mouseY > tableY - tableH/2) && (mouseY < tableY + tableH/2))
    {
      float tempx;
      float tempy;
      tempx = ( game[0].x - mouseX)/10;
      tempy = ( game[0].y - mouseY)/10;
      game[0].dx = tempx;
      game[0].dy = tempy;
    }

}
