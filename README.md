# scalainthecity

Technology stack:

- ZIO (v1.0.0-RC21-2)
- STTP (v2.2.1)
- Circe (v0.14.0-M1)

on Scala 2.13.3

## Taken from ["100 with ZIO Test by Adam Fraser: Scala in the City Conference"](https://www.youtube.com/watch?v=qDFfVinjDPQ)

### Attention: Seems like there is a bug in the code!

1. Run the program. 
2. Change parameters in `def run(args: List[String]): ZIO[ZEnv, Nothing, ExitCode]`
3. Run `topContributors("scala", 25)`
4. Run `topContributors("python", 25)`
5. Run `topContributors("javascript", 25)`

On my machine it runs only once or maybe twice and then it stops working and fails with ExitCode 1.
