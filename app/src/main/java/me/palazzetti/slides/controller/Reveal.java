package me.palazzetti.slides.controller;

public class Reveal {
    public static String nextSlide() {
        return "Reveal.next();";
    }

    public static String previousSlide() {
        return "Reveal.prev();";
    }

    public static String overview() {
        return "Reveal.toggleOverview();";
    }
}
