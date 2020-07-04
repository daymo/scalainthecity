# scalainthecity

Technology stack:

- ZIO (v1.0.0-RC21-2)
- STTP (v2.2.1)
- Circe (v0.14.0-M1)

on Scala 2.13.3

## Taken from ["100 with ZIO Test by Adam Fraser: Scala in the City Conference"](https://www.youtube.com/watch?v=qDFfVinjDPQ)

After one or two runs of the program it will stop working with an unspecific error.

### Steps to reproduce 

1. Run the program. 
2. Change parameters in `def run(args: List[String]): ZIO[ZEnv, Nothing, ExitCode]`
3. Run `topContributors("scala", 25)`
4. Run `topContributors("python", 25)`
5. Run `topContributors("javascript", 25)`

It has probably thrown an exception by now but the real error is not so obvious. (Spoiler: Github API rate-limit error)

6. Add `.tapError(e => UIO(println(s"error message ${e.asInstanceOf[HttpError].body}")))` in method `getContributors`

Here's the real message `{"message":"API rate limit exceeded for xxx.xxx.xx.xxx. (But here's the good news: Authenticated requests get a higher rate limit. Check out the documentation for more details.)","documentation_url":"https://developer.github.com/v3/#rate-limiting"}`

### STTP Error Passing Info

Using Throwable's `getMessage` with `HttpError` prints `null`. It would also be helpful if parsing sttp's error to string includes the actual error message which it does curently not.

There's a design mistake IMHO in Sttp client, their custom `ResponseError` is a subtype of `Throwable` and they aren't passing the actual error message upwards which is why ZIO stack trace isn't printing anything helpful.
