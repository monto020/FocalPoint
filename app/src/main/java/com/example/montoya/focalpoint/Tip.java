package com.example.montoya.focalpoint;

import java.util.ArrayList;

/**
 * Created by Montoya on 5/9/2016.
 */
public class Tip {
    private String tip;
    public static ArrayList<Tip> tipArrayList;

    Tip(String tip){this.tip=tip;}
    Tip(){tip="";}

    public static void populateTipsList(){
        tipArrayList = new ArrayList<Tip>();
        tipArrayList.add(new Tip("1. Do creative work first.\n\n" +
                "Typically, we do mindless work first and build up to the toughest tasks. That drains your energy and lowers your focus." +
                " \"An hour into doing your work, you've got a lot less capacity than (at the beginning)." +
                "\n-https://www.entrepreneur.com/article/225321"));

        tipArrayList.add(new Tip("2. Check up on yourself.\n\n" +
                "Distractions can be internal as well as external, so start by looking within. If you're all over the place, ask yourself what's really going on." +
                " What's the source of your flightiness or anxiety? What do you need to be working on in your life?" +
                "\n-http://www.inc.com"));

        tipArrayList.add(new Tip("3. Clean it up.\n\n" +
                "What's the state of your office or workspace? If it's dirty, disorganized, or cluttered, invest some time in clearing it out so you can focus." +
                "\n-http://www.inc.com"));

        tipArrayList.add(new Tip("4. Yes, the mind takes bribes. Instead of telling it NOT to worry about another, " +
                "lesser priority (which will cause your mind to think about the very thing it's not supposed to think about!), assign it a single task with start-stop time parameters." +
                "\n-https://www.verywell.com"));

        tipArrayList.add(new Tip("5. Next time your mind is a million miles away, simply look around you and really SEE your surroundings. Study that exquisite flower in the vase. " +
                "Get up close to the picture on the wall and marvel at the artist's craftmanship." +
                "\n-https://www.verywell.com"));

        tipArrayList.add(new Tip("6. Break it down.\n\n" +
                "Especially when distractions are high, make tasks smaller and break down your large projects into smaller tasks to help you concentrate and give you a sense of accomplishment and progress." +
                "\n-http://www.inc.com"));

        tipArrayList.add(new Tip("7. Train your mind like a muscle.\n\n" +
                "When multitasking is the norm, your brain quickly adapts. " +
                "You lose the ability to focus as distraction becomes a habit." +
                "\n-https://www.entrepreneur.com/article/225321"));

        tipArrayList.add(new Tip("8. Establish Rituals: Rituals in your work life are valuable. The mindmap offers a lot of good suggestions for rituals " +
                "from decluttering your workspace to healthy habits like sleep and exercise." +
                "\n-http://www.bethkanter.org"));

        tipArrayList.add(new Tip("9. Pinpoint the cause.\n\n" +
                "Once you have your internal priorities sorted out, look at more external causes. Is it your office setup? An intrusive co-worker? " +
                "A lack of skill, ideas, or time for something you need to be doing? Burnout? When you can identify the cause, you can fix the effect." +
                "\n-http://www.inc.com"));

        tipArrayList.add(new Tip("10. There are two kinds of people—those who have learned how to work through frustration and those who wish they had. " +
                "From now on, if you're in the middle of a task and tempted to give up, just do FIVE MORE.Read five more pages. Finish five more math problems. " +
                "Work five more minutes." +
                "\n-https://www.verywell.com"));

        tipArrayList.add(new Tip("11. Just Say No: Maybe you are going to say no to social media for a day and go to meet with people, take a class, read a book, or talk a walk. " +
                "    When I’m feeling most overwhelmed,  I take a break.   Even if it is just to get up and walk around my desk." +
                "\n-http://www.bethkanter.org"));

        tipArrayList.add(new Tip("12. Give yourself a break.\n\n" +
                "One of the keys to doing great work is to know when to take a break. When you start to feel distracted, take a break, and then reassess and refocus yourself." +
                "It doesn't just act as a reward--a short break can help your mind become clearer." +
                "\n-http://www.inc.com"));

        tipArrayList.add(new Tip("13. Pinpoint What Breaks Your Concentration and Remove Those Triggers." +
                "\n-http://lifehacker.com"));

        tipArrayList.add(new Tip("14. Notice where and when you focus best, then allocate your toughest tasks for those moments." +
                "\n-https://www.entrepreneur.com/article/225321"));

        tipArrayList.add(new Tip("15. Reflection: Reflection doesn’t have to be a huge amount of time to be effective.   I’m taking ten minutes every morning to practice some visual recording skills like drawing to create my" +
                " “3 Most Important Things for Today List.”" +
                "\n-http://www.bethkanter.org"));

        tipArrayList.add(new Tip("16. Wear headphones or earplugs: If loud noises are the biggest cause of distraction then the most logical approach is to remove them from the equation." +
                "\n-http://lifehacker.com"));

        tipArrayList.add(new Tip("17. Set a deadline.\n\n" +
                "If you're working on a complex task, it takes an average of 90 minutes to accomplish anything worthwhile--and about 30 minutes just to get your mind on the task." +
                " Once you are in the flow, set a concentrated period of time--and when the time runs out, stop. It's easier to stay focused when you have an end in sight." +
                "\n-http://www.inc.com"));

        tipArrayList.add(new Tip("18.Next time you're about to postpone a responsibility ask yourself, \"Do I have to do this? Do I want it done so it's not on my mind? Will it be any easier later?\"" +
                "\n-https://www.verywell.com"));

        tipArrayList.add(new Tip("19. Go offline.\n\n" +
                "Some of the biggest sources of distraction come from email, social media, and cell phones. " +
                "If you want real focus, take yourself offline until you've accomplished what you need to do." +
                "\n-http://www.inc.com"));

        tipArrayList.add(new Tip("20. Practice concentration by turning off all distractions and committing your attention to a single task." +
                " Start small, maybe five minutes per day, and work up to larger chunks of time" +
                "\n-https://www.entrepreneur.com/article/225321"));



    }


    public String getTip(){return tip;}
}
