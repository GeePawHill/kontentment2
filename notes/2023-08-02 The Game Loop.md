Okayyyyyy. Last commit is a bust: it has a test in it that cannot run. That's unfortunate, because the thing it's meant
to test, the poorly named GameLoop, has a bug. Let's grok that part, first.

The GameLoop is how we update the presentation. It takes a constructor
argument that is a lambda called tick(), and at the beginning of every animation frame, it calls that lambda. The
lambda takes a double that repreesnts the delta in seconds from the previous frame.

There are several problems with this, but one of them seems to be an outright bug: in its first call, the delta should
be something like 0.1666667, and it is a varied number between 6 and 10 seconds. Whoops.

The class came from online. I brought it into a previous project, used it, and kinda ignored it. That was where I first
noted the bug, but it didn't affect my use-case then.

It was for a little toy thing, and I used toy rules. Now I wanta use big-boy rules.

So? You say you're a TDD'er. Write a test for the bug and fix it.

Aye, there's the rub. You see, the GameLoop uses a JavaFx AnimationTimer, and that class is an "awkward collaborator".
For a closer look at
awkward vs graceful collaborators, see here:

https://www.geepawhill.org/2021/02/12/awkward-graceful/

Awkward collaborators can be awkward in different ways.

The way AnimationTimer is awkward is that it will not work
unless the JavaFx UI thread has been started.

This is true of many of the components of JavaFx, but not all of them. (The various observer objects, for instance, work
just fine w/o that thread running.)

And here's a lesson, btw: when you derive simply and directly from an awkward collaborator, you *are* an awkward
collaborator.

So, what to do here? I have a bug. I need to fix it. For a TDD'er there are three choices:

1) Hell with it, write the JavaFx thread test.
2) Hell with it, just read and reason until you find and fix the bug.
3) Hell with it, re-factor the code so it isn't awkward to test.

We're going with 3.

Why not #1, test it by using a JavaFx thread?

Now, I know how to get a JavaFx thread running w/o putting up any windows or doing any sleep's, but I do not like to do
that.

Microtests should be blindingly fast, a few milliseconds each, and starting that thread consumes nearly a second.

And there's more: working that way will be inherently multi-threaded, and nothing makes messier, harder-to-write,
harder-to-run, harder-to-read tests than threading.

And you know what?

When the tests are harder-to-write, harder-to-read, and harder-to-run, do you know what I do?

I don't run them.

Whoops.

Okay, why not #2: don't test it.

Welllll, ngl, if that one bug was the only infelicity, I might try to slide by and go for #2 here.

But in this case, a) there's that bug, b) there's other stuff I think I want to change, c) a drive-by inspection didn't
make that bug seem obvious.

If I'm going to be changing this code a fair bit, I am going to wish it were tested.

Learning TDD has a lot of "fool me once, shame on you, fool me 147 times across 17 projects over 5 years, shame on me"
in it.

It makes you realize that even very simple code like this, a handful of mutable longs being added & set in various ways,
can confuse you very quickly.

And you begin to realize how much time you save when you *know* the code does what it says it does.

Broad strategy here:

Make the dependency has-a instead of an is-a. That is, convert the GameLoop to use delegation instead of inheritance.

Then make that dependency on a new interface, one that we can supply a testable implementation for as well as the
shipping implementation, which will use the AnimationTimer.

Finally, test to our heart's content.

Step: God I hate that name. I christen thee . . . Clock!

Step: Create a new class, ClockTimer, that's derived from AnimationTimer. Give that class a constructor arg that's a
lambda, and do the required overload of AnimationTimer::handle to call the lambda.

Step: Give Clock a new field "timer"  of ClockTimer, and pass it the lambda of calling ClockTimer's handle() method.

