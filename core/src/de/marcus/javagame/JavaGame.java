package de.marcus.javagame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.alsclo.voronoi.Voronoi;
import de.alsclo.voronoi.graph.Graph;
import de.alsclo.voronoi.graph.Point;
import de.marcus.javagame.misc.CustomComparator;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class JavaGame extends ApplicationAdapter {
    Batch batch;
    OrthographicCamera camera;

    Viewport viewport;
    ShapeRenderer shapeRenderer;
    List<Point> list;
    Voronoi voronoi;
    Graph graph;
    Color color = Color.WHITE;
    List<Point> sortedPoints;

    @Override
    public void create() {


        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera(50, 50);
        viewport = new FillViewport(camera.viewportWidth, camera.viewportHeight, camera);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            double x = Math.random() * 50;
            double y = Math.random() * 50;
            Point p = new Point(x, y);
            list.add(p);
        }

        voronoi = new Voronoi(list);
        graph = voronoi.relax().getGraph();

//		for (Point point : graph.getSitePoints().stream().sorted(new CustomComparator()).collect(Collectors.toList())) {
//
//			System.out.println(point.x + " " + point.y);
//		}

        sortedPoints = graph.getSitePoints().stream().sorted(new CustomComparator()).collect(Collectors.toList());

        for (Point point : sortedPoints) {
            System.out.println("x: " + point.x + " , y:" + point.y);
        }


//        Gdx.graphics.setContinuousRendering(false);
//        Gdx.graphics.requestRendering();
    }

    @Override
    public void render() {
        super.render();


        camera.update();

        shapeRenderer.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);

        shapeRenderer.setAutoShapeType(true);


        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);


        int counter = 0;
        for (Point point : sortedPoints) {
            if(counter % 20 == 0)
                colorNext();

            shapeRenderer.setColor(color);
            shapeRenderer.circle((float) point.x, (float) point.y, 0.05f, 20);
            counter++;
        }
        color = Color.WHITE;

        shapeRenderer.end();
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin();
        graph.edgeStream().filter(e -> e.getA() != null && e.getB() != null).forEach(e -> {
            Point a = e.getA().getLocation();
            Point b = e.getB().getLocation();
            shapeRenderer.line((float) a.x, (float) a.y, (float) b.x, (float) b.y);

        });
        shapeRenderer.end();

//		shapeRenderer.begin();
//		for (DTriangle t : del.getTriangles()) {
//			shapeRenderer.line((float) t.a.x,(float) t.a.y,(float) t.b.x,(float) t.b.y);
//		}
//		shapeRenderer.end();
//
//		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//		for (DPoint point : list) {
//			shapeRenderer.rect((float) point.x,(float) point.y,0.1f,0.1f,Color.RED, Color.RED,Color.RED,Color.RED);
//		}
//		shapeRenderer.end();
    }

    public void update() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    @Override
    public void dispose() {

    }

    public void colorNext() {
        System.out.println("Change color");

        if (Color.WHITE.equals(color)) {
            color = Color.BLUE;
        } else if (Color.BLUE.equals(color)) {
            color = Color.GREEN;
        } else if (Color.GREEN.equals(color)) {
            color = Color.RED;
        } else if(color.equals(Color.RED)) {
            color = Color.WHITE;
        }
    }

}
