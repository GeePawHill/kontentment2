Okayyyyyy. Last commit is a bust: it has a test in it that cannot run. That's unfortunate, because the thing it's meant
to test, the poorly named GameLoop, has a bug. Let's grok that part, first.

The GameLoop is how we update the presentation. It takes a constructor
argument that is a lambda called `tick()`, and at the beginning of every animation frame, it calls that lambda. The
lambda takes a double that repreesnts the delta in seconds from the previous frame.

There are several problems with this, but one of them seems to be an outright bug: in its first call, the delta should
be something like 0.1666667, and it is a varied number between 6 and 10 seconds. Whoops.

The class came from online. I brought it into a previous project, used it, and kinda ignored it. That was where I first
noted the bug, but it didn't affect my use-case then.

There are a couple of other infelicities about it, too. Not so much bugs, but things that aren't quite fit-for-purpose.
Not important for the moment. I'd just like to find and fix my bug.

So? Write a test, fix the bug, innit. "You say you're a programmer."

Aye, there's the rub. You see, the GameLoop uses a JavaFx AnimationTimer, and that class is an "awkward collaborator".
For a closer look at
awkward vs graceful collaborators, see here: https://www.geepawhill.org/2021/02/12/awkward-graceful/

Awkward collaborators can be awkward in different ways. The way AnimationTimer is awkward is that it will not work
unless the JavaFx UI thread has been started. This is true of many of the components of JavaFx, but not all of them. (
The various observer objects, for instance, work just fine w/o that thread running.)

Now, I know how to get a JavaFx thread running w/o putting up any windows or doing any sleep's, but I do not like to do
that. Microtests should be blindingly fast, a few milliseconds each, and starting that thread consumes nearly a second.

And if we just go ahead and do it anyway, there's another issue: the junit tests themselves don't run on that UI thread,
so now you're writing multi-threaded tests and using either sleeps or latches. Here's the thing: when the tests take too
long to run, or too long to write, or too long to read, do you know what I do? I
don't run them, write them, or read them. Oops.

So, what to do here? I have a bug. I need to fix it. For a TDD'er there are three choices:

1) Hell with it, write the JavaFx thread test.
2) Hell with it, just read and reason until you find and fix the bug.
3) Hell with it, re-factor the code so it isn't awkward to test.

So. Hell with it, we're gonna refactor the code so it isn't awkward to test.

Now, to tell you the truth, if that bug were the only issue, I might go with #2. But once I get that out of the way, as
I say, there are other things I'll want to change. I don't like changing code I can't test.

Here we go:

Step: Rename it. I hate that name. I christen thee . . . Clock.
