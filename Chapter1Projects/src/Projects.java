import java.util.ArrayList;
import java.util.List;

public class Projects
{
    public static void main(String[] args)
    {
        helloWorld();
        System.out.println();
        victory();
        System.out.println();
        shapes();
        System.out.println();
        song();
        System.out.println();
        christmasSong();
    }

    public static void helloWorld()
    {
        System.out.println("Hello World");
        System.out.println("Life if roblox");
        System.out.println("Call me asaparagus");
        System.out.println("Let's go golfing");
        System.out.println("Empty line");

    }
    public static void victory()
    {
        System.out.println("///////////////////////");
        for (int i = 0; i < 5; ++i)
        {
            victoryIsMine();
        }
    }
    public static void victoryIsMine()
    {
        System.out.println("|| Victory is mine! ||");
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
    }
    public static void shapes()
    {
        topShape();
        bottomShape();
        middleShape();
        topShape();
        bottomShape();
        middleShape();
        bottomShape();
        topShape();
        middleShape();
        bottomShape();
    }

    public static void topShape()
    {
        System.out.println("  ______  ");
        System.out.println(" /      \\");
        System.out.println("/        \\");
    }

    public static void middleShape()
    {
        System.out.println("_\"_'_\"_'_\"_");
    }

    public static void bottomShape()
    {
        System.out.println("\\        /");
        System.out.println(" \\      /");
        System.out.println("  ______  ");
    }

    public static void song()
    {
        intro("fly");
        refrain();
        // verse
        intro("spider");
        System.out.println("That wriggled and iggled and jiggled inside her.");
        swallowedThe("spider", "fly");
        refrain();
        // verse
        intro("bird");
        System.out.println("How absurd to swallow a bird.");
        swallowedThe("bird", "spider");
        swallowedThe("spider", "fly");
        refrain();
        // verse
        intro("cat");
        System.out.println("Imagine that to swallow a cat.");
        swallowedThe("cat", "bird");
        swallowedThe("bird", "spider");
        swallowedThe("spider", "fly");
        refrain();
        // verse
        intro("dog");
        System.out.println("What a hog to swallow a dog.");
        swallowedThe("dog", "cat");
        swallowedThe("cat", "bird");
        swallowedThe("bird", "spider");
        swallowedThe("spider", "fly");
        refrain();
        // verse
        intro("horse");
        System.out.println("She died of course.");


    }

    public static void intro(String what)
    {
        System.out.print("There was an old lady who swallowed a ");
        System.out.print(what);
        System.out.println(",");
    }
    public static void refrain()
    {
        System.out.println("I don't know why she swallowed that fly,");
        System.out.println("Perhaps she'll die.");
        System.out.println();
    }

    public static void swallowedThe(String first, String second)
    {
        System.out.print("She swallowed the ");
        System.out.print(first);
        System.out.print(" to catch the ");
        System.out.print(second);
        System.out.println(",");

    }

    public static void christmasSong()
    {
        ArrayList<String> lyrics = new ArrayList<String>();
        lyrics.add("a partridge in a pear tree.");
        lyrics.add("two turtle doves, and");
        lyrics.add("three french hens,");
        lyrics.add("four calling birds,");
        lyrics.add("five golden rings,");
        lyrics.add("six geese a-laying,");
        lyrics.add("seven swans a-swimming,");
        lyrics.add("eight maids a-milking,");
        lyrics.add("nine ladies dancing,");
        lyrics.add("ten lords a-leaping,");
        lyrics.add("eleven pipers piping,");
        lyrics.add("twelve drummers drumming,");

        ArrayList<String> starts = new ArrayList<String>();
        starts.add("first");
        starts.add("second");
        starts.add("third");
        starts.add("fourth");
        starts.add("fifth");
        starts.add("sixth");
        starts.add("seventh");
        starts.add("eight");
        starts.add("ninth");
        starts.add("tenth");
        starts.add("eleventh");
        starts.add("twelfth");



        for (int i = 0; i < lyrics.size(); ++i)
        {
            Start(starts.get(i));
            body(lyrics, i);
            System.out.println();
        }
    }

    public static void Start(String day)
    {
        System.out.print("On the ");
        System.out.print(day);
        System.out.println(" day of Christmas,");
        System.out.println("my true love sent to me");
    }

    public static void body(ArrayList<String> lyrics, int count)
    {
        for (int i = count; i >= 0; --i)
        {
            System.out.println(lyrics.get(i));
        }
    }
}