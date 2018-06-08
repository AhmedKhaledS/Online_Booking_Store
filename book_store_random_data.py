from faker import Faker
from random import randint
import numpy
import csv

BOOK_COUNT = 1000000
AUTHORS_COUNT = 500000
PUBLISHER_COUNT = 100000

fake = Faker('it_IT')

categ = ["Science", "Art", "Religion", "History", "Geography"]

# PUBLISHER
publisher_id = [str(i) for i in range(PUBLISHER_COUNT)]
publisher_name = [fake.name() for _ in range(PUBLISHER_COUNT)]
publisher_address = [fake.street_address() for _ in range(PUBLISHER_COUNT)]
telephone_number = [fake.phone_number() for _ in range(PUBLISHER_COUNT)]

with open('publisher.csv', 'w') as f:
    wr = csv.writer(f, lineterminator='\n')
    for i in range(PUBLISHER_COUNT):
        wr.writerow([publisher_id[i]] + [publisher_name[i]] + [publisher_address[i]] + [telephone_number[i]])


# BOOK
isbn = [str(i) for i in range(BOOK_COUNT)]
book_titles = [fake.sentence(3) for _ in range(BOOK_COUNT)]
publisher_id_ = [publisher_id[randint(0, PUBLISHER_COUNT-1)] for _ in range(BOOK_COUNT)]
publication_year = [fake.date() for _ in range(BOOK_COUNT)]
category = [categ[randint(0, 4)] for _ in range(BOOK_COUNT)]
price = [numpy.random.uniform(0.0, 9999.9) for _ in range(BOOK_COUNT)]
no_of_copies = [randint(5, 5000) for _ in range(BOOK_COUNT)]
min_quantity = [randint(0, 1000) for _ in range(BOOK_COUNT)]


with open('book.csv', 'w') as f:
    wr = csv.writer(f, lineterminator='\n')
    for i in range(BOOK_COUNT):
        wr.writerow([isbn[i]] + [book_titles[i]] + [publisher_id_[i]] + [publication_year[i]] + [category[i]] + [price[i]] + [no_of_copies[i]] + [min_quantity[i]])


# BOOK_AUTHORS
isbn_ = [isbn[randint(0, BOOK_COUNT-1)] for _ in range(AUTHORS_COUNT)]
author_name = [fake.name() for _ in range(AUTHORS_COUNT)]

with open('book_authors.csv', 'w') as f:
    wr = csv.writer(f, lineterminator='\n')
    for i in range(AUTHORS_COUNT):
        wr.writerow([isbn_[i]] + [author_name[i]])
