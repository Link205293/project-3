// Project 3
// Garrett Stoddard
// Objects time

// globals

float left, right, top, bot, mid;
//float cueX,  cueY,  cueDX,  cueDY;
//float yellow1X,  yellow1Y,  yellow1DX,  yellow1DY;
//float blue2X, blue2Y, blue2DX, blue2DY;
//float red3X,  red3Y,  red3DX,  red3DY;
float friction = .986;
// dummy check varibles
float button1X, button1Y = 50, button1q = 50, button1p = 20;
float checkX[] = new float[16];
float checkY[] = new float[16];
float ballX[] = new float[16];
float ballY[] = new float[16];
float ballDX[] = new float[16];
float ballDY[] = new float[16];
Ball cue, one, two, three, four, five;

// setup method

void setup()
{
  size( 1250, 750 );
  left = 75;
  right = width - 75;
  top = 150;
  bot = height - 75;
  mid = width/2;
  reset();
}

// frames
void draw()
{
  scene();
  balls();
}

void scene()
{
  background(#067920);
}


void reset()
{
  ballX[0] = mid/2;
  ballY[0] = (top + bot)/2;
  ballDX[0] = 0;
  ballDY[0] = 0;
  for( int i = 1; i < 16; i = i + 1)
  {
    checkX[i] = random(mid + 30, right - 30);
    checkY[i] = random(top + 30, bot - 30); 
    ballDX[i] = 0;
    ballDY[i] = 0;
  }
  // Checks the placement of the second ball and doesnt let it spawn untill it 
  // isn't on top of another ball
  for( int i = 1; i < 16; i = i + 1)
  {
    for( int j = 1; j < 16; j = j +1)
    {
      if( j == i)
      {
        // avoids an infinte loop
      }
      else
      {
        while(dist(checkX[i], checkY[i], checkX[j], checkY[j] ) < 60)
         {
           checkX[i] = random(mid + 30, right - 30);
           checkY[i] = random(top + 30, bot - 30);
         }
      }
    }
    ballX[i] = checkX[i];
    ballY[i] = checkY[i];
  }
  cue = new Ball(ballX[0], ballY[0], ballDX[0], ballDY[0], #FFFFFF);
  one = new Ball(ballX[1], ballY[1], ballDX[1], ballDY[1], #FADD35);
  two = new Ball(ballX[2], ballY[2], ballDX[2], ballDY[2], #434AFF);
  three = new Ball(ballX[3], ballY[3], ballDX[3], ballDY[3], #FC351F);
  four = new Ball(ballX[4], ballY[4], ballDX[4], ballDY[4], #A329FC);
  five = new Ball(ballX[5], ballY[5], ballDX[5], ballDY[5], #FC981C);
   
}

void collision( Ball q, Ball w ) {
  if ( q.hit( w.x,w.y ) ) {
    float swap;
    swap=q.dx;  
    q.dx=w.dx;  
    w.dx=swap;      // Swap the velocities.
    swap=q.dy;  
    q.dy=w.dy;  
    w.dy=swap;
   
  }
}

void balls()
{
  collision(cue, one);
  collision(cue, two);
  collision(cue, three);
  collision(cue, four);
  collision(cue, five);
  
  collision(one, two);
  collision(one, three);
  collision(one, four);
  collision(one, five);
  
  collision(two, three);
  collision(two, four);
  collision(two, five);
  
  collision(three, four);
  collision(three, five);
  
  collision(four, five);
  
  cue.move();
  one.move();
  two.move();
  three.move();
  four.move();
  five.move();
  
  cue.show();
  one.show();
  two.show();
  three.show();
  four.show();
  five.show();
  
}

class Ball 
{
  //Properties
  float x, y, dx, dy;
  color c;
  float name;
  boolean striped;
  
  // constructor
  Ball( float tempX, float tempY, float tempDX, float tempDY, color tempC)
    {
      x = tempX;
      y = tempY;
      dx = tempDX;
      dy = tempDY;
      c = tempC;
    }
  //methods
  void show()
  {
    fill(c);
    ellipse(x, y, 60, 60);
    fill(0);
  }
  
  void move()
  {
    if (x > width - 30)
    {
      dx = - dx;
      x = width - 30;
    }
    else if ( x < 30)
    {
      dx = - dx;
      x = 30;
    }
    if (y > height - 30)
    {
      dy = - dy;
      y = height - 30;
    }
    else if ( y < 30)
    {
      dy = - dy;
      y = 30;
    }
    x = x + dx;
    y = y + dy;
  }
  
  boolean hit( float x, float y)
  {
    if( dist ( x, y, this.x, this.y) < 60) 
    {
      return true;
    }
    else return false;
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
  float tempx;
  float tempy;
  tempx = ( cue.x - mouseX)/10;
  tempy = ( cue.y - mouseY)/10;
  cue.dx = tempx;
  cue.dy = tempy;
  
}
