/**
 * A standalone Java exercise component.
 */
package gameCore;

import java.awt.*;

public abstract class GameObject {
  protected double x, y;
  protected ID id;
  protected double velX, velY;
  protected double health;
  protected double damage;

  public double getDamage() {
    return damage;
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public ID getId() {
    return id;
  }

  public void setId(ID id) {
    this.id = id;
  }

  public double getVelX() {
    return velX;
  }

  public void setVelX(double velX) {
    this.velX = velX;
  }

  public double getVelY() {
    return velY;
  }

  public void setVelY(double velY) {
    this.velY = velY;
  }

  public GameObject(double x, double y, ID id) {
    this.x = x;
    this.y = y;
    this.id = id;
  }

  public abstract void tick();

  public abstract void render(Graphics g);

  public abstract Rectangle getBounds();
}
