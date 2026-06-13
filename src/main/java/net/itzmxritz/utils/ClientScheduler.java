package net.itzmxritz.utils;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClientScheduler {

    private static final List<ScheduledTask> tasks = new ArrayList<>();

    private static class ScheduledTask {
        int ticks;
        Runnable action;

        ScheduledTask(int ticks, Runnable action) {
            this.ticks = ticks;
            this.action = action;
        }
    }

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Iterator<ScheduledTask> it = tasks.iterator();

            while (it.hasNext()) {
                ScheduledTask task = it.next();
                task.ticks--;

                if (task.ticks <= 0) {
                    task.action.run();
                    it.remove();
                }
            }
        });
    }

    public static void runLater(int ticks, Runnable action) {
        tasks.add(new ScheduledTask(ticks, action));
    }

    public static void runNextTick(Runnable action) {
        runLater(1, action);
    }
}
