package com.calyr.data

import com.calyr.domain.Movie

class MovieRepository {

    fun obtainMovies(): List<Movie> {
        return listOf(
            Movie(
                id = "1",
             title = "Venom: The Last Dance",
             description = "Eddie and Venom are on the run. Hunted by both of their worlds and with the net closing in, the duo are forced into a devastating decision that will bring the curtains down on Venom and Eddie's last dance.",
             posterPath = "/3V4kLQg0kSqPLctI5ziYWabAZYF.jpg"
             ),
            Movie(
                id = "2",
                title = "The Wild Robot",
                description = "After a shipwreck, an intelligent robot called Roz is stranded on an uninhabited island. To survive the harsh environment, Roz bonds with the island's animals and cares for an orphaned baby goose.",
                posterPath = "/wTnV3PCVW5O92JMrFvvrRcV39RU.jpg"
            ),
            Movie(
                id = "3",
                title = "Terrifier 3",
                description = "Five years after surviving Art the Clown's Halloween massacre, Sienna and Jonathan are still struggling to rebuild their shattered lives. As the holiday season approaches, they try to embrace the Christmas spirit and leave the horrors of the past behind. But just when they think they're safe, Art returns, determined to turn their holiday cheer into a new nightmare. The festive season quickly unravels as Art unleashes his twisted brand of terror, proving that no holiday is safe.",
                posterPath = "/63xYQj1BwRFielxsBDXvHIJyXVm.jpg"
            ),
            Movie(
                id = "4",
                title = "Transformers One",
                description = "The untold origin story of Optimus Prime and Megatron, better known as sworn enemies, but once were friends bonded like brothers who changed the fate of Cybertron forever.",
                posterPath = "/qrwI2T844nrBUv3eDwQZRDdgSFs.jpg"
            ),
            Movie(
                id = "5",
                title = "Alien: Romulus",
                description = "While scavenging the deep ends of a derelict space station, a group of young space colonizers come face to face with the most terrifying life form in the universe.",
                posterPath = "/b33nnKl1GSFbao4l3fZDDqsMx0F.jpg"
            )

        )
    }
}